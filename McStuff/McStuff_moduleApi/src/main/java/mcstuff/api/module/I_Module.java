package mcstuff.api.module;

import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import mcstuff.api.I_ModuleHost;

public interface I_Module {
	MenuItem getMenuItem();
	String getTitle();
	void initialize(I_ModuleHost host);
	void show(Stage stage);
	void hide();
}
