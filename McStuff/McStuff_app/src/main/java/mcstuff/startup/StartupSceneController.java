package mcstuff.startup;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import mcstuff.ApplicationConfig;
import mcstuff.api.module.I_Module;

@Component
public class StartupSceneController implements Initializable {
	
	private static final Logger logger = LoggerFactory.getLogger(StartupSceneController.class);
	
	@Autowired
	private ApplicationConfig appConfig;
	
	@FXML AnchorPane rootPane;
	@FXML TilePane paneModules;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Set<I_Module> modules = appConfig.getModules();
		for(I_Module module : modules) {
			Button btnModule = new Button();
			btnModule.getStyleClass().add("btn_record-sales");
			btnModule.setText(module.getTitle());
			btnModule.onActionProperty().set(new EventHandler<ActionEvent>() {				
				@Override
				public void handle(ActionEvent event) {
					logger.info("Module {} activiated",new Object[] {module});
					module.getSelectionCallback().call(event);
					event.consume();
				}
			});
			paneModules.getChildren().add(btnModule);
		}

	}

}
