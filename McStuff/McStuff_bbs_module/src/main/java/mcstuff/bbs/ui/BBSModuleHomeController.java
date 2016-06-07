package mcstuff.bbs.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import mcstuff.bbs.model.BBSConnection;
import mcstuff.javafx.spring.SpringFXMLLoader.NodeWithController;
import mcstuff.modules.BBSModule;

@Component
public class BBSModuleHomeController implements Initializable {
	
	@Autowired
	protected BBSModule bbsModule;
	
	protected URL locationURL;
	protected ResourceBundle resourceBundle;
	
	@FXML ScrollPane paneContent;

	@FXML BorderPane layoutContent;

	@FXML BorderPane layoutConnection;

	@FXML ListView<BBSConnection> lvConnections;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.locationURL = location;
		this.resourceBundle = resources;
		
		bbsModule.currentConnectionProperty().addListener(new InvalidationListener() {			
			@Override
			public void invalidated(Observable observable) {
				onConnectionInvalidated();
			}
		});
		
		lvConnections.setCellFactory(new Callback<ListView<BBSConnection>, ListCell<BBSConnection>>() {
			@Override
			public ListCell<BBSConnection> call(ListView<BBSConnection> lv) {
				return bbsModule.getAppConfig().getApplicationContext().getBean(ConnectionCell.class);
			}
		});
		
		lvConnections.itemsProperty().bind(bbsModule.connectionListProperty());
		onConnectionInvalidated();
	}
	
	public void onConnectionInvalidated() {
		BBSConnection currentConnection = bbsModule.getCurrentConnection();
		if(currentConnection == null) {
			// show the connections UI
			layoutConnection.setVisible(true);
		} else {
			layoutConnection.setVisible(false);
		}
	}
	
	public void setContent(Stage stage, Parent bbsRoot) {
		stage.setWidth(bbsRoot.prefWidth(-1) + 30);
		stage.setHeight(bbsRoot.prefHeight(-1) + 55);
		paneContent.setContent(bbsRoot);
		
		layoutConnection.setVisible(false);
	}
	
	@Component
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	static class ConnectionCell extends ListCell<BBSConnection> {
		
		@Autowired
		protected BBSModule bbsModule;
		
		@Autowired
		BBSModuleHomeController parentController;

		Parent cellTemplate;
		ConnectionCellController controller;
				
		@PostConstruct
		protected void init() {
        	NodeWithController nodeWithController = bbsModule.getAppConfig().getFXMLLoader().load("/mcstuff/bbs/ui/ConnectionCell.fxml");
        	cellTemplate = (Parent)nodeWithController.node; 
        	controller = (ConnectionCellController) nodeWithController.controller;
		}
		
        @Override
        protected void updateItem(BBSConnection item, boolean empty) {
        	controller.setConnection(item);
        	setGraphic(cellTemplate);        	
        }
    }

}
