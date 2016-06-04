package mcstuff.bbs.ui;

import javax.script.ScriptEngine;

import mcstuff.modules.BBSModule;

public interface I_BBSController {
	public BBSModule getModule();
	public ScriptEngine getScriptEngine();
}
