package mcstuff.api.module;

import javafx.stage.Stage;
import javafx.util.Callback;

public interface I_Module {
	@Override
	boolean equals(Object obj);

	Callback<Void, Void> getActivationCallback();

	String getTitle();

	@Override
	int hashCode();

	void hide();

	void initialize(I_ModuleHost host);

	void show(Stage stage) throws Exception;

	@Override
	String toString();
}
