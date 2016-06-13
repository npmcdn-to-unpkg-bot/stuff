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
package mcstuff.bbs.ui;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import mcstuff.bbs.model.BBSConnection;
import mcstuff.modules.BBSModule;

@Component
public class BBSModuleHomeController implements Initializable {
	
	@Autowired
	protected BBSModule bbsModule;
	
	protected URL locationURL;
	protected ResourceBundle resourceBundle;
	
	private ObjectProperty<BBSConnection> currentConnection = new SimpleObjectProperty<>();
	
	@FXML ScrollPane paneContent;

	@FXML BorderPane layoutContent;

	@FXML SplitPane layoutConnection;

	@FXML ListView<BBSConnection> lvConnections;

	@FXML MenuBar mbBBS;

	@FXML StackPane rootPane;

	@FXML Label lblConnectionTitle;

	@FXML GridPane paneConnectionEdit;

	@FXML TextField txtConnectionName;

	@FXML TextArea taConnectionDescription;

	@FXML Button btnSaveChanges;

	@FXML Button btnCancelChanges;

	@FXML Button btnRemove;

	@FXML Button btnNewConnection;

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
		
		lvConnections.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BBSConnection>() {
			@Override
			public void changed(ObservableValue<? extends BBSConnection> observable, BBSConnection oldValue,
					BBSConnection newValue) {
				onConnectionSelectionChange(oldValue, newValue);
			}
		});
		lvConnections.getFocusModel().focus(0);
		lvConnections.scrollTo(0);
		
		if(lvConnections.getItems().size() > 0) {
			lvConnections.getSelectionModel().clearAndSelect(0);
		} else {
			onConnectionSelectionChange(null, null);
		}
	}
	
	@FXML public void onCloseConnection() {
		bbsModule.setCurrentConnection(null);
	}
	
	@FXML public void onSaveChanges() {
		bbsModule.updateConnection(getCurrentConnection());
	}

	@FXML public void onCancelChanges() {
		if(getCurrentConnection() != null && getCurrentConnection().getId() > 0) {
    		int idxConn = bbsModule.getConnectionList().indexOf(getCurrentConnection());
    		bbsModule.restoreConnection(getCurrentConnection());
    		lvConnections.getSelectionModel().clearAndSelect(idxConn);
		} else {
			onRemoveConnection();
		}
	}
	
	@FXML public void onRemoveConnection() {
		if(getCurrentConnection() != null) {
			if(currentConnection.get().getId() > 0) {
				bbsModule.removeConnection(getCurrentConnection());
			}
			onConnectionSelectionChange(getCurrentConnection(), null);
		}
	}
	
	@FXML public void onNewConnection() {
		BBSConnection connection = new BBSConnection();
		lvConnections.getSelectionModel().clearSelection();
		onConnectionSelectionChange(null, connection);
	}

	
	public void onConnectionSelectionChange(BBSConnection oldValue,
			BBSConnection newValue) {
		if(oldValue != null) {			
			Bindings.unbindBidirectional(oldValue.nameProperty(), txtConnectionName.textProperty());
			txtConnectionName.setText(null);
			Bindings.unbindBidirectional(oldValue.descriptionProperty(), taConnectionDescription.textProperty());
			taConnectionDescription.setText(null);
			oldValue.trackChanges(false);
		}
		
		btnSaveChanges.visibleProperty().unbind();
		btnSaveChanges.setVisible(false);
		btnCancelChanges.visibleProperty().unbind();
		btnCancelChanges.setVisible(false);
		btnRemove.setVisible(false);
		currentConnection.set(newValue);
		
		if(newValue != null) {
			txtConnectionName.setText(newValue.getName());
			Bindings.bindBidirectional(newValue.nameProperty(), txtConnectionName.textProperty());
			taConnectionDescription.setText(newValue.getDescription());
			Bindings.bindBidirectional(newValue.descriptionProperty(), taConnectionDescription.textProperty());
			newValue.trackChanges(true);
			
			btnSaveChanges.visibleProperty().bind(newValue.dirtyProperty());
			btnCancelChanges.visibleProperty().bind(newValue.dirtyProperty());
			
			btnRemove.setVisible(true);
		} 
	}
	
	public void onConnectionInvalidated() {
		BBSConnection currentConnection = bbsModule.getCurrentConnection();
		if(currentConnection == null) {
			// show the connections UI
			bbsModule.getStage().setWidth(rootPane.prefWidth(-1) + 30);
			bbsModule.getStage().setHeight(rootPane.prefHeight(-1) + 55);
			lblConnectionTitle.setText("");
			layoutConnection.setVisible(true);
		} else {
			lblConnectionTitle.setText(currentConnection.getName());
			layoutConnection.setVisible(false);
			
		}
	}
	
	public void setContent(Stage stage, Parent bbsRoot) {
		stage.setWidth(bbsRoot.prefWidth(-1) + 30);
		stage.setHeight(bbsRoot.prefHeight(-1) + 85);
		paneContent.setContent(bbsRoot);
		
		layoutConnection.setVisible(false);
	}

	public final ObjectProperty<BBSConnection> currentConnectionProperty() {
		return this.currentConnection;
	}
	public final BBSConnection getCurrentConnection() {
		return this.currentConnectionProperty().get();
	}
	public final void setCurrentConnection(final BBSConnection currentConnection) {
		this.currentConnectionProperty().set(currentConnection);
	}	
	
}
