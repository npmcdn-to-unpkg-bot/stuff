package mcstuff.startup;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;

import mcstuff.Application;
import mcstuff.ApplicationConfig;
import mcstuff.api.module.I_Module;

@Component
public class StartupSceneController implements Initializable {

	@Autowired
	private ApplicationConfig appConfig;

	@FXML
	AnchorPane rootPane;
	@FXML
	TilePane paneModules;

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		final Set<I_Module> modules = appConfig.getModules();
		for (final I_Module module : modules) {
			final Button btnModule = new Button();
			btnModule.getStyleClass().add("btn_record-sales");
			btnModule.setText(module.getTitle());
			btnModule.onActionProperty().set(new EventHandler<ActionEvent>() {
				@Override
				public void handle(final ActionEvent event) {
					Application.getInstance().activateModule(module);
					event.consume();
				}
			});
			paneModules.getChildren().add(btnModule);
		}

	}

}
