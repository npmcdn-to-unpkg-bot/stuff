package mcstuff.api.module;

import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public interface I_Module {
	MenuItem getMenuItem();
	String getTitle();
	void initialize();
	void show(Stage stage);
	void hide();
}
