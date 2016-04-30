package mcstuff.api;

import org.springframework.context.ApplicationContext;

import javafx.stage.Stage;
import mcstuff.api.module.I_Module;

public interface I_ModuleHost {
	ApplicationContext getContext();
	
	Stage getCurrentStage();
	void setCurrentStage(Stage stage);
	
	I_Module getCurrentModule();
	void setCurrentModule(I_Module module);
	
	void showDefaultView();
}
