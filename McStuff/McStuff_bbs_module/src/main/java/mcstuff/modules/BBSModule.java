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
	
	private BBSConnection currentConnection;
	
	public BBSModule() {
		setTitle("BBS Module");
	}

	@Override
	public void initialize(I_ModuleHost host) {
		super.initialize(host);
	}
	
	public BBSConnection getCurrentConnection() {
		return currentConnection;
	}

	public void setCurrentConnection(BBSConnection currentConnection) {
		this.currentConnection = currentConnection;
	}
	
	@Override
	public void show(Stage stage) {
		showScene(stage, "/mcstuff/bbs/ui/BBSModuleHome.fxml");
	}
		
	public void onConnection() {
		final Parent root = (Parent) bbsFXMLLoader.load("Index.fxml");
	}
}
