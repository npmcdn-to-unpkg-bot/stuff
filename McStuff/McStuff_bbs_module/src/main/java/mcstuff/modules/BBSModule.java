package mcstuff.modules;

import org.springframework.stereotype.Component;

import mcstuff.api.module.I_Module;
import mcstuff.api.module.I_ModuleHost;
import mcstuff.api.module.ModuleBase;

@Component
public class BBSModule extends ModuleBase implements I_Module {

	@Override
	public void initialize(I_ModuleHost host) {
		super.initialize(host);
		setTitle("BBS Module");
	}
}
