package mcstuff.bbs.ui;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mcstuff.modules.BBSModule;
import javafx.scene.layout.AnchorPane;

@Component
public class BBSModuleHomeController implements Initializable {
	
	@Autowired
	private BBSModule bbsModule;
	
	@FXML ScrollPane paneContent;

	@FXML BorderPane layoutContent;

	@FXML AnchorPane layoutConnection;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bbsModule.connect();
	}
	
	public void setContent(Stage stage, Parent bbsRoot) {
		stage.setWidth(bbsRoot.prefWidth(-1) + 30);
		stage.setHeight(bbsRoot.prefHeight(-1) + 55);
		paneContent.setContent(bbsRoot);
		
		layoutConnection.setVisible(false);
	}

}
