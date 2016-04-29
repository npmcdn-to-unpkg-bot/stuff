package mcstuff.api;

import org.springframework.context.ApplicationContext;

import javafx.stage.Stage;

public interface I_ModuleHost {
	ApplicationContext getContext();
	
	Stage getCurrentStage();
	void setCurrentStage(Stage stage, boolean bShow, boolean bWait);
}
