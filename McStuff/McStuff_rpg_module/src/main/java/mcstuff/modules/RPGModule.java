package mcstuff.modules;

import org.springframework.stereotype.Component;

import javafx.stage.Stage;

import mcstuff.api.module.I_Module;
import mcstuff.api.module.I_ModuleHost;
import mcstuff.api.module.ModuleBase;

@Component
public class RPGModule extends ModuleBase implements I_Module {
	
	public RPGModule() {
		setTitle("RPG Module");
	}
	
	@Override
	public void initialize(I_ModuleHost host) {
		super.initialize(host);
	}
	
	@Override
	public void show(final Stage stage) throws Exception {	
		showScene(stage, "/mcstuff/rpg/ui/RPGMain.fxml");
		super.show(stage);
	}

}
