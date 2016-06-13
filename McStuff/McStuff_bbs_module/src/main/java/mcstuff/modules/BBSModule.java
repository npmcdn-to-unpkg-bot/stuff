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
package mcstuff.modules;

import java.util.Collection;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import mcstuff.api.module.I_Module;
import mcstuff.api.module.I_ModuleHost;
import mcstuff.api.module.ModuleBase;
import mcstuff.bbs.model.BBSConnection;
import mcstuff.bbs.model.BBSConnectionRepository;
import mcstuff.bbs.ui.BBSFXMLLoader;
import mcstuff.bbs.ui.BBSModuleHomeController;
import mcstuff.bbs.ui.I_BBSController;

@Configuration
public class BBSModule extends ModuleBase implements I_Module {
	private static final Logger logger = LoggerFactory.getLogger(BBSModule.class);
	
	@Bean
	public ScriptEngine bbsScriptEngine() {
		ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");
		scriptEngine.put("BBSModule", this);
		//engine.eval(new FileReader(SCRIPT));
		return scriptEngine;
	}
	
	@Bean
	CommandLineRunner init(final BBSConnectionRepository connectionRepository) {
		return new CommandLineRunner() {
			
			@Override
			public void run(final String... args) throws Exception {
				BBSConnection connection = connectionRepository.findByBbsTag("apc");
				if(connection == null) {
					connection = new BBSConnection();
    				connection.setName("Amateur Programmer's Club BBS");
    				connection.setDescription("A BBS Dedicated to the hobbyist programmer who enjoys the sport...");
    				connection.setServerURL("http://localhost:8090");
    				connection.setBbsTag("apc");
    				connection.setUserName("misha");
    				connection.setPassword("masha");
    				connectionRepository.save(connection);
				}
			}
		};
	}
	
	@Autowired
	protected BBSFXMLLoader bbsFXMLLoader;
	
	@Autowired
	protected BBSConnectionRepository bbsConnectionRepository;
	
	@Autowired
	protected BBSModuleHomeController bbsModuleHomeController;
	
	private I_BBSController currentController;
	
	private ObjectProperty<BBSConnection> currentConnection = new SimpleObjectProperty<>();
	private ReadOnlyListWrapper<BBSConnection> connectionList = new ReadOnlyListWrapper<>();		
	private ObjectProperty<Stage> stage = new SimpleObjectProperty<>();
	
	public BBSModule() {
		setTitle("BBS Module");
	}

	@Override
	public void initialize(I_ModuleHost host) {
		super.initialize(host);	
		
		connectionList.set(FXCollections.observableArrayList());
		connectionList.addAll((Collection<? extends BBSConnection>) bbsConnectionRepository.findAll());
	}
	
	@Override
	public void show(Stage stage) throws Exception {
		this.stage.set(stage);
				
		showScene(stage, "/mcstuff/bbs/ui/BBSModuleHome.fxml");
	}
		
	public void connect() {
		try {
			final Pane bbsRoot = (Pane) bbsFXMLLoader.load("Index.fxml");
			bbsModuleHomeController.setContent(getStage(), bbsRoot);
		} catch(Exception ex) {
			String connectionName = getCurrentConnection() != null && 
					getCurrentConnection().getName() != null ? getCurrentConnection().getName() : "[Unknown Connection]";
			logger.error("Error connecting to {}", connectionName, ex);
			showErrorPopup("Connection Error",
					String.format("Error Connecting to %s: %s", connectionName, ex.getMessage()));
			setCurrentConnection(null);
		}
	}
	
	public void restoreConnection(BBSConnection connection) {
		int idxConn = connectionList.indexOf(connection);
		connectionList.remove(connection);
		connection = bbsConnectionRepository.findById(connection.getId());
		connectionList.add(idxConn, connection);
	}

	public void updateConnection(BBSConnection connection) {
		try {
    		bbsConnectionRepository.save(connection);
    		connection.setDirty(false);
    		if(!connectionList.contains(connection)) {
    			connectionList.add(connection);
    		}
		} catch(Exception ex) {
			String connectionName = connection != null && connection.getName() != null ? connection.getName() : "[Unknown Connection]";
			logger.error("Error updating connection {}", connectionName, ex);
			showErrorPopup("Update Error",
					String.format("Error updating connection %s: %s", connectionName, ex.getMessage()));
		}
	}
	
	public void removeConnection(BBSConnection connection) {
		try {
    		connectionList.remove(connection);
    		bbsConnectionRepository.delete(connection);
		} catch(Exception ex) {
			String connectionName = connection != null && connection.getName() != null ? connection.getName() : "[Unknown Connection]";
			logger.error("Error removing connection {}", connectionName, ex);
			showErrorPopup("Remove Error",
					String.format("Error removing connection %s: %s",connectionName, ex.getMessage()));
		}
	}
	
	public void showErrorPopup(String title, String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(title);
		alert.setContentText(message);
		alert.show();

	}
	
	public I_BBSController getCurrentController() {
		return currentController;
	}
	public void setCurrentController(I_BBSController currentController) {
		this.currentController = currentController;
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

	public final ReadOnlyListProperty<BBSConnection> connectionListProperty() {
		return this.connectionList.getReadOnlyProperty();
	}
	public final ObservableList<BBSConnection> getConnectionList() {
		return this.connectionListProperty().get();
	}

	public final ObjectProperty<Stage> stageProperty() {
		return this.stage;
	}
	public final Stage getStage() {
		return this.stageProperty().get();
	}
	public final void setStage(final Stage stage) {
		this.stageProperty().set(stage);
	}
	
	
	
	
}
