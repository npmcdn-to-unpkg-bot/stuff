package mcstuff.modules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.scene.Parent;
import javafx.scene.Scene;
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
	public String getTitle() {
		return title;
	}

	private boolean visible = false;
	public boolean isVisible() {
		return visible;
	}

	@Override
	public Callback<Void, Void> getActivationCallback() {
		final I_Module module = this;
		return new Callback<Void, Void>() {
			@Override
			public Void call(Void vArg) {
				appConfig.setCurrentModule(module);
				return null;
			}
		};
	}
	
	@Override
	public void show(Stage stage) {
		Parent root = (Parent) appConfig.getFXMLLoader().load("/mcstuff/rpg/ui/RPGMain.fxml");
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		visible = true;
	}

	@Override
	public void hide() {
		visible = false;
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
