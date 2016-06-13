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
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

import mcstuff.bbs.model.BBSConnection;
import mcstuff.modules.BBSModule;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ConnectionCellController implements Initializable {
	
	@Autowired
	protected BBSModule bbsModule;
	
	private ObjectProperty<BBSConnection> connection = new SimpleObjectProperty<>();
	private ObjectProperty<ListCell<BBSConnection>> cell = new SimpleObjectProperty<>();

	@FXML Button btnConnect;

	@FXML HBox connectionCellPane;

	@FXML Label lblConnectionName;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	@FXML public void onConnect(Event event) {
		bbsModule.setCurrentConnection(getConnection());
		bbsModule.connect();
	}


	public void updateSelected(boolean selected) {
    	btnConnect.setVisible(selected);	
	}
	
	protected void onKeyEvent(KeyEvent keyEvent) {
		if(keyEvent.getCode() == KeyCode.ENTER) {
			onConnect(keyEvent);
			keyEvent.consume();
		}
	}

	
	public final ObjectProperty<BBSConnection> connectionProperty() {
		return this.connection;
	}
	public final mcstuff.bbs.model.BBSConnection getConnection() {
		return this.connectionProperty().get();
	}
	public final void setConnection(final mcstuff.bbs.model.BBSConnection connection) {
		this.connectionProperty().set(connection);
	}
	

	public final ObjectProperty<ListCell<BBSConnection>> cellProperty() {
		return this.cell;
	}
	public final javafx.scene.control.ListCell<mcstuff.bbs.model.BBSConnection> getCell() {
		return this.cellProperty().get();
	}
	public final void setCell(final javafx.scene.control.ListCell<mcstuff.bbs.model.BBSConnection> cell) {
		this.cellProperty().set(cell);
		cell.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				onKeyEvent(event);
			}
		});
	}

	
	
	
	
}
