package mcstuff.api.module;

import javafx.stage.Stage;
import javafx.util.Callback;

public interface I_Module {
	void initialize(I_ModuleHost host);
	String getTitle();
	Callback<Void, Void> getSelectionCallback();
	void show(Stage stage);
	void hide();
	int hashCode();
	boolean equals(Object obj);
	String toString();
}
