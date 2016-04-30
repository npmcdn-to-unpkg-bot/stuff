package mcstuff;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mcstuff.api.ClassEnumerator;
import mcstuff.api.I_ModuleHost;
import mcstuff.api.module.I_Module;
import mcstuff.javafx.spring.AbstractJavaFxApplicationSupport;
import mcstuff.javafx.spring.SpringFXMLLoader;

@SpringBootApplication
public class Application extends AbstractJavaFxApplicationSupport implements I_ModuleHost {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	private static final String MODULE_PACKAGE = "mcstuff.modules";
	private static Application instance;
	
	private Stage currentStage;
	private Scene defaultView;
	private Set<I_Module> modules = new HashSet<>();
	private I_Module currentModule;

	@Autowired
	private SpringFXMLLoader fxmlLoader;

	@Bean
	@Primary
	public DataSource dataSource() {
		return (DataSource) DataSourceBuilder.create().username("dbUser").password("dbPass")
				.url("jdbc:derby:mcstuff_db;create=true").build();
	}

	@Value("${app.ui.title:McStuff}") //
	private String windowTitle;
	
	public static Application getInstance() {
		return instance;
	}

	@Override
	public void start(Stage stage) throws Exception {
		instance = this;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					loadModules();

					stage.setTitle(windowTitle);

					Parent root = (Parent) fxmlLoader.load("/mcstuff/startup/StartupScene.fxml");
					defaultView = new Scene(root);
					
					setCurrentStage(stage);
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
					modules.add(module);
				}
			}
		} catch (Throwable t) {
			logger.error("Error loading modules, shutting down", t);
			Platform.exit();
		}
	}
	
	@Override
	public void showDefaultView() {
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

	@Override
	public Stage getCurrentStage() {
		return currentStage;
	}

	@Override
	public void setCurrentStage(Stage stage) {
		if (currentStage != null) {
			currentStage.hide();
		}
		currentStage = stage;
	}
	
	public Set<I_Module> getModules() {
		return modules;
	}
	
	@Override
	public I_Module getCurrentModule() {
		return currentModule;
	}
	
	@Override
	public void setCurrentModule(I_Module module) {
		if(currentModule != null) {
			currentModule.hide();
		}
		currentModule = module;
		if(currentModule != null) {
			currentModule.show(currentStage);
		} else {
			showDefaultView();
		}
	}

}
