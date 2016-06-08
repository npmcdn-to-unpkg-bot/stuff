package mcstuff.modules;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	}
	
	@Override
	public void show(Stage stage) throws Exception {
		this.stage.set(stage);
		
		connectionList.set(FXCollections.observableArrayList());
		BBSConnection connection = new BBSConnection();
		connection.setName("Amateur Programmer's Club BBS");
		connection.setDescription("A BBS Dedicated to the hobbyist programmer who enjoys the sport...");
		connection.setServerURL("http://localhost:8090");
		connection.setBBSTag("apc");
		connection.setUserName("misha");
		connection.setPassword("mash");
		connectionList.add(connection);
		
		connection = new BBSConnection();
		connection.setName("BBS");
		connection.setServerURL("http://localhost:8090");
		connection.setBBSTag("apc");
		connection.setUserName("misha");
		connection.setPassword("masha");
		connectionList.add(connection);

		
		showScene(stage, "/mcstuff/bbs/ui/BBSModuleHome.fxml");
	}
		
	public void connect() {
		try {
			final Pane bbsRoot = (Pane) bbsFXMLLoader.load("Index.fxml");
			bbsModuleHomeController.setContent(getStage(), bbsRoot);
		} catch(Exception ex) {			
			logger.error("Error connecting to {}", getCurrentConnection().getName(), ex);
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Connection Error");
			alert.setHeaderText("Connection Error");
			String s = String.format("Error Connecting to %s: %s", getCurrentConnection().getName(), ex.getMessage());
			alert.setContentText(s);
			alert.show();

			setCurrentConnection(null);
		}
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
