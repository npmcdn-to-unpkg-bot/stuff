package mcrpg.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mcrpg.app.spring.AbstractJavaFxApplicationSupport;
import mcrpg.app.spring.SpringFXMLLoader;

@Lazy
@SpringBootApplication
public class McRPGApplication extends AbstractJavaFxApplicationSupport {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public static McRPGApplication instance;
	public static McRPGApplication getInstance() {
		return instance;
	}

	@Override
	public void start(Stage startStage) throws Exception {
		instance = this;
		logger.info("Starting up");
		
		Parent root = (Parent) SpringFXMLLoader.load("McRPGStartup.fxml", McRPGStartup.class);

		Scene scene = new Scene(root);

		startStage.setTitle("McRPG");
		startStage.setScene(scene);
		startStage.show();

	}

	public static void main(String[] args) {
		launchApp(McRPGApplication.class, args);
	}

}
