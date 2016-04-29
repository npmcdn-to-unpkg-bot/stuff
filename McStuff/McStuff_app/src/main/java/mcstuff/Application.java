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

	private Stage currentStage;
	private Set<I_Module> modules = new HashSet<>();

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

	@Override
	public void start(Stage stage) throws Exception {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					loadModules();

					stage.setTitle(windowTitle);

					Parent root = (Parent) fxmlLoader.load("/mcstuff/startup/StartupScene.fxml");
					stage.setScene(new Scene(root));
					stage.setResizable(true);
					stage.centerOnScreen();
					setCurrentStage(stage, true, false);
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
	public void setCurrentStage(Stage stage, boolean bShow, boolean bWait) {
		if (currentStage != null) {
			currentStage.hide();
		}
		this.currentStage = stage;
		if (bShow) {
			if (bWait) {
				this.currentStage.showAndWait();
			} else {
				this.currentStage.show();
			}
		}
	}

}
