package mcstuff.modules;

import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import mcstuff.api.module.I_Module;
import mcstuff.api.module.ModuleBase;

@Component
public class RPGModule extends ModuleBase implements I_Module {

	@Override
	public Callback<ActionEvent, Void> getSelectionCallback() {
		return new Callback<ActionEvent, Void>() {
			@Override
			public Void call(ActionEvent event) {
				return onSelection(event);
			}
		};
	}
	
	@Override
	public String getTitle() {
		return "McStuff RPG Module";
	}

	@Override
	public void show(Stage stage) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}
	
	public Void onSelection(ActionEvent event) {
		getHost().setCurrentModule(this);
		return null;		
	}

}
