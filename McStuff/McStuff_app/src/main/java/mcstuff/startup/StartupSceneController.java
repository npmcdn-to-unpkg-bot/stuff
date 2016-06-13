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
package mcstuff.startup;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import mcstuff.Application;
import mcstuff.ApplicationConfig;
import mcstuff.api.module.I_Module;

@Component
public class StartupSceneController implements Initializable {

	@Autowired
	private ApplicationConfig appConfig;

	@FXML
	Pane rootPane;
	@FXML
	TilePane paneModules;

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		final Set<I_Module> modules = appConfig.getModules();
		for (final I_Module module : modules) {
			final Button btnModule = new Button();
			btnModule.getStyleClass().add("btnModule");
			btnModule.setText(module.getTitle());
			btnModule.onActionProperty().set(new EventHandler<ActionEvent>() {
				@Override
				public void handle(final ActionEvent event) {
					Application.getInstance().activateModule(module);
					event.consume();
				}
			});
			paneModules.getChildren().add(btnModule);
		}

	}

}
