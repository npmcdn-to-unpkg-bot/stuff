package mcstuff.api.module;

import javafx.stage.Stage;
import mcstuff.api.I_ModuleHost;

public abstract class ModuleBase implements I_Module {
	
	private I_ModuleHost host;
	public I_ModuleHost getHost() {
		return host;
	}

	@Override
	public void initialize(I_ModuleHost host) {
		this.host = host;
	}

	@Override
	public void show(Stage stage) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

}
