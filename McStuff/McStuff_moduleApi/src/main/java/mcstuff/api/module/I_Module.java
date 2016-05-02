package mcstuff.api.module;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import mcstuff.api.I_ModuleHost;

public interface I_Module {
	void initialize(I_ModuleHost host);
	String getTitle();
	Callback<ActionEvent, Void> getSelectionCallback();
	void show(Stage stage);
	void hide();
	int hashCode();
	boolean equals(Object obj);
	String toString();
}
