package mcstuff.bbs.ui;

import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.fxml.FXMLLoader;
import mcstuff.ApplicationConfig;
import mcstuff.bbs.model.BBSConnection;
import mcstuff.modules.BBSModule;

@Component("bbsFXMLLoader")
public class BBSFXMLLoader {
		
	@Autowired
	protected ApplicationConfig config;
	
	@Autowired
	protected BBSModule bbsModule;
	
	@Autowired
	protected I_BBSController bbsController;
	
	public Object load(final String url) {
		String fullUrl = url;
		try {
			//BBSConnection connection = bbsModule.getCurrentConnection();
			BBSConnection connection = new BBSConnection();
			connection.setServerUrl("http://localhost:8090");
			connection.setBBSTag("apc");
			connection.setUserName("misha");
			connection.setPassword("masha");
			
			Authenticator.setDefault (new Authenticator() {
			    protected PasswordAuthentication getPasswordAuthentication() {
			        return new PasswordAuthentication (connection.getUserName(), connection.getPassword().toCharArray());
			    }
			});
			
			String sFullUrl = connection.getBaseUrl() + (connection.getBaseUrl().endsWith("/")?"":"/")
				+ url;
			URL fullURL = new URL(sFullUrl);
			FXMLLoader loader = new FXMLLoader(fullURL);
			HttpURLConnection urlConnection = (HttpURLConnection) fullURL.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.connect();
			loader.setController(bbsController);
			return loader.load(urlConnection.getInputStream());
		} catch (final Exception e) {
			e.printStackTrace();
			throw new RuntimeException(String.format("Failed to load BBS FXML file '%s' from server", fullUrl), e);
		}
	}
	
	

}
