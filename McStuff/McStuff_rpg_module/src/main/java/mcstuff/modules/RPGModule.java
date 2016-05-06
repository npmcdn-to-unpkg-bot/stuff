package mcstuff.modules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mcstuff.ApplicationConfig;
import mcstuff.api.module.I_Module;
import mcstuff.api.module.ModuleBase;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

@Component
public class RPGModule extends ModuleBase implements I_Module {

	@Autowired
	private ApplicationConfig appConfig;

	private final String title = "McStuff RPG Module";
	private boolean visible = false;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final RPGModule other = (RPGModule) obj;
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}

	@Override
	public Callback<Void, Void> getActivationCallback() {
		final I_Module module = this;
		return new Callback<Void, Void>() {
			@Override
			public Void call(final Void vArg) {
				appConfig.setCurrentModule(module);
				return null;
			}
		};
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (title == null ? 0 : title.hashCode());
		return result;
	}

	@Override
	public void hide() {
		visible = false;
	}

	public boolean isVisible() {
		return visible;
	}

	@Override
	public void show(final Stage stage) {
		final Parent root = (Parent) appConfig.getFXMLLoader().load("/mcstuff/rpg/ui/RPGMain.fxml");
		final Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		visible = true;
	}

	@Override
	public String toString() {
		return "RPGModule [title=" + title + "]";
	}

}
