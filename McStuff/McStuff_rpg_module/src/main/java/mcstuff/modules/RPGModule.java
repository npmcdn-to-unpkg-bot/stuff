package mcstuff.modules;

import org.springframework.stereotype.Component;

import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import mcstuff.api.I_ModuleHost;
import mcstuff.api.module.I_Module;
import mcstuff.api.module.ModuleBase;

@Component
public class RPGModule extends ModuleBase implements I_Module {

	@Override
	public MenuItem getMenuItem() {
		return null;
	}

	@Override
	public String getTitle() {
		return "McStuff RPG Module";
	}

	@Override
	public void initialize(I_ModuleHost host) {
		// TODO Auto-generated method stub

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
