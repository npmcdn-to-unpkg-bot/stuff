package mcstuff.bbs.ui;

import java.io.IOException;
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

	public Object load(final String url) throws IOException  {
		BBSConnection connection = bbsModule.getCurrentConnection();
		Authenticator.setDefault(new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(connection.getUserName(), connection.getPassword().toCharArray());
			}
		});

		String sFullUrl = connection.getBaseURL() + (connection.getBaseURL().endsWith("/") ? "" : "/") + url;
		URL fullURL = new URL(sFullUrl);
		FXMLLoader loader = new FXMLLoader(fullURL);
		HttpURLConnection urlConnection = (HttpURLConnection) fullURL.openConnection();
		urlConnection.setRequestMethod("GET");
		urlConnection.connect();
		loader.setController(bbsController);
		return loader.load(urlConnection.getInputStream());
	}

}
