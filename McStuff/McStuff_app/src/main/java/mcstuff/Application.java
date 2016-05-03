package mcstuff;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mcstuff.api.module.I_Module;
import mcstuff.api.module.I_ModuleHost;
import mcstuff.javafx.spring.AbstractJavaFxApplicationSupport;
import mcstuff.util.ClassEnumerator;

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
	
	public static void main(String[] args) {
		launchApp(Application.class, args);
	}
	
	private Stage mainStage = null;
	private Stage moduleStage = null;
	private Scene defaultView = null;

	@Override
	public void start(Stage stage) throws Exception {
		instance = this;
		mainStage = stage;
		appConfig.setModuleHost(this);
		Platform.setImplicitExit(false);
		
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					loadModules();
					
					mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {						
						@Override
						public void handle(WindowEvent event) {
							shutDown();
							event.consume();
						}
					});
					
					moduleStage = new Stage();
					moduleStage.setOnCloseRequest(new EventHandler<WindowEvent>() {			
						@Override
						public void handle(WindowEvent event) {
							moduleStage.hide();
							showDefaultView();
							event.consume();				
						}
					});


					mainStage.setTitle(appConfig.getAppTitle());

					Parent root = (Parent) appConfig.getFXMLLoader().load("/mcstuff/startup/StartupScene.fxml");
					defaultView = new Scene(root);
					
					appConfig.setCurrentStage(mainStage);
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
	
	public void activateModule(I_Module module) {
		logger.info("Activating {}",new Object[] {module});
		appConfig.setCurrentStage(moduleStage);
		moduleStage.setTitle(module.getTitle());
		module.getSelectionCallback().call(null);
		if(!mainStage.isShowing() && !moduleStage.isShowing()) {
			logger.info("Module did not display, showing main window");
			mainStage.show();
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
	public void showModule(I_Module module) {
		try {
			module.show(moduleStage);
		} catch(Exception ex) {
			logger.error("Error loading module {} : {}", module, ex);
			moduleStage.hide();
			mainStage.show();
		}
		
	}

	@Override
	public void shutDown() {
		Platform.exit();
	}


	
	
}
