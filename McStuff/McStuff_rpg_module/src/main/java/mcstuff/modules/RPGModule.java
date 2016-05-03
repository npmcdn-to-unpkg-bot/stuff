package mcstuff.modules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.stage.Stage;
import javafx.util.Callback;
import mcstuff.ApplicationConfig;
import mcstuff.api.module.I_Module;
import mcstuff.api.module.ModuleBase;

@Component
public class RPGModule extends ModuleBase implements I_Module {

	@Autowired
	private ApplicationConfig appConfig;
	
	private String title = "McStuff RPG Module";

	@Override
	public Callback<Void, Void> getSelectionCallback() {
		return new Callback<Void, Void>() {
			@Override
			public Void call(Void vArg) {
				return onSelection();
			}
		};
	}
	
	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void show(Stage stage) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}
	
	public Void onSelection() {
		appConfig.setCurrentModule(this);
		return null;		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RPGModule other = (RPGModule) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RPGModule [title=" + title + "]";
	}
	
}
