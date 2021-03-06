/* Copyright (C) 2016+ Michael Cassidy - All Rights Reserved
	This file is a part of McStuff

    McStuff is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    McStuff is distributed as sample code in the hope that it will be 
    useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with McStuff.  If not, see <http://www.gnu.org/licenses/>. 
 */
package mcstuff;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import mcstuff.api.module.I_Module;
import mcstuff.api.module.I_ModuleHost;
import mcstuff.javafx.spring.AbstractJavaFxApplicationSupport;
import mcstuff.javafx.spring.SpringFXMLLoader.NodeWithController;
import mcstuff.util.ClassEnumerator;

@SpringBootApplication
@EntityScan
@EnableJpaRepositories
@PropertySource("classpath:/application.properties")
public class Application extends AbstractJavaFxApplicationSupport implements I_ModuleHost {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	private static final String MODULE_PACKAGE = "mcstuff.modules";
	private static Application instance;

	public static Application getInstance() {
		return instance;
	}

	public static void main(final String[] args) {
		launchApp(Application.class, args);
	}
	
	@Autowired
	private ApplicationConfig appConfig;

	private Stage mainStage = null;
	private Stage moduleStage = null;
	private Scene defaultView = null;

	public void activateModule(final I_Module module) {
		logger.info("Activating {}", new Object[] {
			module
		});
		appConfig.setCurrentStage(moduleStage);
		moduleStage.setTitle(module.getTitle());
		module.getActivationCallback().call(null);
		if (!mainStage.isShowing() && !moduleStage.isShowing()) {
			logger.info("Module did not display, showing main window");
			mainStage.show();
		}
	}

	public void loadModules() {
		try {
			final List<Class<?>> classes = ClassEnumerator.getClassesForPackage(MODULE_PACKAGE);
			for (final Class<?> clazz : classes) {
				if (I_Module.class.isAssignableFrom(clazz)) {
					final I_Module module = (I_Module) applicationContext.getBean(clazz);
					logger.info("Initializing Module: " + module.getTitle());
					module.initialize(this);
					appConfig.getModules().add(module);
				}
			}
		} catch (final Throwable t) {
			logger.error("Error loading modules, shutting down", t);
			Platform.exit();
		}
	}

	@Override
	public void showDefaultView() {
		appConfig.setCurrentStage(mainStage);
		mainStage.setScene(defaultView);
		mainStage.setResizable(true);
		mainStage.centerOnScreen();
		mainStage.show();
	}

	@Override
	public void showModule(final I_Module module) {
		try {
			module.show(moduleStage);
		} catch (final Exception ex) {
			logger.error("Error loading module {} : {}", module, ex);
			moduleStage.hide();
			mainStage.show();
		}

	}

	@Override
	public void shutDown() {
		Platform.exit();
	}

	@Override
	public void start(final Stage stage) throws Exception {
		instance = this;
		mainStage = stage;
		appConfig.setModuleHost(this);
		Platform.setImplicitExit(false);

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					loadModules();
					mainStage.getIcons().add(new Image("/mcstuff/startup/logo.png"));
					mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
						@Override
						public void handle(final WindowEvent event) {
							shutDown();
							event.consume();
						}
					});

					moduleStage = new Stage();
					moduleStage.getIcons().add(new Image("/mcstuff/startup/logo.png"));
					moduleStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
						@Override
						public void handle(final WindowEvent event) {
							if(appConfig.getCurrentModule() != null) {
								appConfig.getCurrentModule().hide();
							}
							moduleStage.hide();
							showDefaultView();
							event.consume();
						}
					});

					mainStage.setTitle(appConfig.getAppTitle());

					NodeWithController nodeWithController = appConfig.getFXMLLoader().load("/mcstuff/startup/StartupScene.fxml");
					final Parent root = (Parent) nodeWithController.node;
					defaultView = new Scene(root);

					appConfig.setCurrentStage(mainStage);
					showDefaultView();

				} catch (final Throwable t) {
					logger.error("Error starting up", t);
					Platform.exit();
				}
			}
		});
	}

	@Override
	public String toString() {
		return "Application [mainStage=" + mainStage + ", moduleStage=" + moduleStage + ", defaultView=" + defaultView
				+ "]";
	}

}
