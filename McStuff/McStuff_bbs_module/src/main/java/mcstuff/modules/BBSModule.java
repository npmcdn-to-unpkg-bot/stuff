package mcstuff.modules;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
	private BBSFXMLLoader bbsFXMLLoader;
	
	@Autowired
	private BBSConnectionRepository bbsConnectionRepository;
	
	@Autowired
	private BBSModuleHomeController bbsModuleHomeController;
	
	private I_BBSController currentController;
	
	private BBSConnection currentConnection;
	
	private Stage stage;
	
	public BBSModule() {
		setTitle("BBS Module");
	}

	@Override
	public void initialize(I_ModuleHost host) {
		super.initialize(host);
	}
	
	public I_BBSController getCurrentController() {
		return currentController;
	}

	public void setCurrentController(I_BBSController currentController) {
		this.currentController = currentController;
	}

	public BBSConnection getCurrentConnection() {
		return currentConnection;
	}

	public void setCurrentConnection(BBSConnection currentConnection) {
		this.currentConnection = currentConnection;
	}
	
	@Override
	public void show(Stage stage) {
		this.stage = stage;
		showScene(stage, "/mcstuff/bbs/ui/BBSModuleHome.fxml");
	}
		
	public void connect() {
		final Parent bbsRoot = (Parent) bbsFXMLLoader.load("Index.fxml");
		bbsModuleHomeController.setContent(stage, bbsRoot);
	}
}
