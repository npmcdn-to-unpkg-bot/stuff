package mcstuff.modules;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
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
	
	
	private Stage stage;
	
	public BBSModule() {
		setTitle("BBS Module");
	}

	@Override
	public void initialize(I_ModuleHost host) {
		super.initialize(host);		
	}
	
	@Override
	public void show(Stage stage) {
		this.stage = stage;
		
		connectionList.set(FXCollections.observableArrayList());
		BBSConnection connection = new BBSConnection();
		connection.setName("Amateur Programmer's Club BBS");
		connection.setServerURL("http://localhost:8090");
		connection.setBBSTag("apc");
		connection.setUserName("misha");
		connection.setPassword("masha");
		connectionList.add(connection);
		
		showScene(stage, "/mcstuff/bbs/ui/BBSModuleHome.fxml");
	}
		
	public void connect() {
		final Parent bbsRoot = (Parent) bbsFXMLLoader.load("Index.fxml");
		bbsModuleHomeController.setContent(stage, bbsRoot);
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
	

	public final mcstuff.bbs.model.BBSConnection getCurrentConnection() {
		return this.currentConnectionProperty().get();
	}
	

	public final void setCurrentConnection(final mcstuff.bbs.model.BBSConnection currentConnection) {
		this.currentConnectionProperty().set(currentConnection);
	}

	public final javafx.beans.property.ReadOnlyListProperty<mcstuff.bbs.model.BBSConnection> connectionListProperty() {
		return this.connectionList.getReadOnlyProperty();
	}
	

	public final javafx.collections.ObservableList<mcstuff.bbs.model.BBSConnection> getConnectionList() {
		return this.connectionListProperty().get();
	}
	
	
}
