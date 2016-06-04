package mcstuff.bbs.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javax.script.ScriptEngine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.fxml.Initializable;
import mcstuff.modules.BBSModule;

@Component
public class BBSController implements Initializable, I_BBSController {
	
	@Autowired
	private BBSModule bbsModule;
	
	@Autowired
	private ScriptEngine scriptEngine;
	
	private URL locationURL;
	private ResourceBundle resourceBundle;


	@Override
	public void initialize(URL url, ResourceBundle bundle) {
		locationURL = url;
		resourceBundle = bundle;
	}
	
	@Override
	public BBSModule getModule() {
		return bbsModule;
	}

	@Override
	public ScriptEngine getScriptEngine() {
		return scriptEngine;
	}

	public URL getLocationURL() {
		return locationURL;
	}

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	

}
