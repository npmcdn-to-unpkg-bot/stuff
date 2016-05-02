package mcstuff;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mcstuff.api.ClassEnumerator;
import mcstuff.api.I_ModuleHost;
import mcstuff.api.module.I_Module;
import mcstuff.javafx.spring.AbstractJavaFxApplicationSupport;

@SpringBootApplication
public class Application extends AbstractJavaFxApplicationSupport implements I_ModuleHost {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	private static final String MODULE_PACKAGE = "mcstuff.modules";
	private static Application instance;
		
	@Autowired
	private ApplicationConfig appConfig;
	
	public static Application getInstance() {
		return instance;
	}
	
	private Scene defaultView;

	@Override
	public void start(Stage stage) throws Exception {
		instance = this;
		appConfig.setModuleHost(this);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					loadModules();

					stage.setTitle(appConfig.getAppTitle());

					Parent root = (Parent) appConfig.getFXMLLoader().load("/mcstuff/startup/StartupScene.fxml");
					defaultView = new Scene(root);
					
					appConfig.setCurrentStage(stage);
					showDefaultView();
					
				} catch (Throwable t) {
					logger.error("Error starting up", t);
					Platform.exit();
				}
			}
		});
	}

	public void loadModules() {
		try {
			List<Class<?>> classes = ClassEnumerator.getClassesForPackage(MODULE_PACKAGE);
			for (Class<?> clazz : classes) {
				if (I_Module.class.isAssignableFrom(clazz)) {
					I_Module module = (I_Module) applicationContext.getBean(clazz);
					logger.info("Initializing Module: " + module.getTitle());
					module.initialize(this);
					appConfig.getModules().add(module);
				}
			}
		} catch (Throwable t) {
			logger.error("Error loading modules, shutting down", t);
			Platform.exit();
		}
	}
	
	@Override
	public void showDefaultView() {
		Stage currentStage = appConfig.getCurrentStage();
		currentStage.setScene(defaultView);
		currentStage.setResizable(true);
		currentStage.centerOnScreen();
		currentStage.show();
	}

	public static void main(String[] args) {
		launchApp(Application.class, args);
	}

	@Override
	public ApplicationContext getContext() {
		return applicationContext;
	}
	
}
