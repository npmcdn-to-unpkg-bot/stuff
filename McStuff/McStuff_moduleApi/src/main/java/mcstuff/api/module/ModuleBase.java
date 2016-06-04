package mcstuff.api.module;

import org.springframework.beans.factory.annotation.Autowired;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

import mcstuff.ApplicationConfig;

public abstract class ModuleBase implements I_Module {
	
	@Autowired
	private ApplicationConfig appConfig;
	public ApplicationConfig getAppConfig() {
		return appConfig;
	}
	
	private String title = null;	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	private boolean visible = false;
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	private I_ModuleHost host;
	public I_ModuleHost getHost() {
		return host;
	}
	
	@Override
	public Callback<Void, Void> getActivationCallback() {
		return new Callback<Void, Void>() {
			@Override
			public Void call(final Void vArg) {
				return onCallBack(vArg);
			}
		};
	}
	
	public Void onCallBack(final Void vArg) {
		appConfig.setCurrentModule(this);
		return null;		
	}

	@Override
	public void hide() {
		setVisible(false);
	}

	@Override
	public void initialize(final I_ModuleHost host) {
		this.host = host;
	}

	@Override
	public void show(final Stage stage) {
		setVisible(true);
	}
	
	public void showScene(Stage stage, String fxmlPath) {
		Parent root = (Parent) getAppConfig().getFXMLLoader().load(fxmlPath);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
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
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		ModuleBase other = (ModuleBase) obj;
		if (title == null) {
			if (other.title != null) return false;
		} else if (!title.equals(other.title)) return false;
		return true;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() +" [title=" + title + 
			", visible=" + visible + ", host=" + host + "]";
	}

}
