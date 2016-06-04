package mcstuff.bbs.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mcstuff.ApplicationConfig;
import mcstuff.bbs.model.BBSConnection;
import mcstuff.javafx.spring.SpringFXMLLoader;
import mcstuff.modules.BBSModule;

@Component("bbsFXMLLoader")
public class BBSFXMLLoader extends SpringFXMLLoader {
		
	@Autowired
	protected ApplicationConfig config;
	
	@Autowired
	protected BBSModule bbsModule;
	
	public Object load(final String url) {
		String fullUrl = url;
		try {
			BBSConnection connection = bbsModule.getCurrentConnection();
			fullUrl = connection.getBaseUrl() + (connection.getBaseUrl().endsWith("/")?"":"/")
				+ url;
			return super.load(fullUrl);
		} catch (final Exception e) {
			e.printStackTrace();
			throw new RuntimeException(String.format("Failed to load BBS FXML file '%s' from server", fullUrl));
		}
	}
	
	@Override
	protected Object controllerFactory(Class<?> aClass) {
		Object controller = super.controllerFactory(aClass);
		return controller;
	}

}
