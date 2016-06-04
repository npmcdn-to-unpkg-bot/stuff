package mcstuff.modules;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import mcstuff.api.module.I_Module;
import mcstuff.api.module.I_ModuleHost;
import mcstuff.api.module.ModuleBase;
import mcstuff.bbs.model.BBSConnection;
import mcstuff.bbs.util.BBSFXMLLoader;

@Component
@Configuration
@EntityScan(basePackages={"mcstuff.bbs.model.BBSConnectionEntity"})
@EnableJpaRepositories(basePackages={"mcstuff.bbs.model.BBSConnectionRepository"})
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
	
	private BBSConnection currentConnection;
	
	public BBSModule() {
		setTitle("BBS Module");
	}

	@Override
	public void initialize(I_ModuleHost host) {
		super.initialize(host);
	}
	
	public BBSFXMLLoader getBBSFXMLLoader() {
		return bbsFXMLLoader;
	}

	public BBSConnection getCurrentConnection() {
		return currentConnection;
	}

	public void setCurrentConnection(BBSConnection currentConnection) {
		this.currentConnection = currentConnection;
	}
	
	
}
