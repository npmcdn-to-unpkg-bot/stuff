/* Copyright (C) 2016+ Michael Cassidy - All Rights Reserved
	This file is a part of McStuff

    McStuff is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    McStuff is distributed as sample code in the hope that it will be 
    useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with McStuff.  If not, see <http://www.gnu.org/licenses/>. 
 */
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
