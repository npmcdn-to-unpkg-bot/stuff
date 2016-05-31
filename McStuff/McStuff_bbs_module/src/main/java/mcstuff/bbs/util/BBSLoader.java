package mcstuff.bbs.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mcstuff.ApplicationConfig;
import mcstuff.bbs.BBSConstants;
import mcstuff.bbs.model.ConnectionEntity;
import mcstuff.javafx.spring.SpringFXMLLoader;

@Component
public class BBSLoader {
	
	@Autowired
	private SpringFXMLLoader springLoader;
	
	@Autowired
	private ApplicationConfig config;
	
	public Object load(final String url) {
		String fullUrl = url;
		try {
			ConnectionEntity connection = (ConnectionEntity) 
					config.getSettings().get(BBSConstants.SETTINGS_KEY_CURRENT_CONNECTION);
			fullUrl = connection.getBaseUrl() + (connection.getBaseUrl().endsWith("/")?"":"/")
				+ url;
			return springLoader.load(fullUrl);
		} catch (final Exception e) {
			e.printStackTrace();
			throw new RuntimeException(String.format("Failed to load BBS FXML file '%s' from server", fullUrl));
		}
	}

}
