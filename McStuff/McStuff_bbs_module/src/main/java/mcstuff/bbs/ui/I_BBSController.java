package mcstuff.bbs.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javax.script.ScriptEngine;

import mcstuff.modules.BBSModule;

public interface I_BBSController {
	public BBSModule getModule();
	public ScriptEngine getScriptEngine();
	
	public URL getLocationURL();
	public ResourceBundle getResourceBundle();
}
