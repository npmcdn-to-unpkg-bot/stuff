package mcstuff.api;

import org.springframework.context.ApplicationContext;

public interface I_ModuleHost {
	ApplicationContext getContext();
		
	void showDefaultView();
}
