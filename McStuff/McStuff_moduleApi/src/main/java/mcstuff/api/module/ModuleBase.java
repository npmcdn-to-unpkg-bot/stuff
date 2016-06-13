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
package mcstuff.api.module;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

import mcstuff.ApplicationConfig;
import mcstuff.javafx.spring.SpringFXMLLoader.NodeWithController;

public abstract class ModuleBase implements I_Module {
	
	@Autowired
	private ApplicationConfig appConfig;
	public ApplicationConfig getAppConfig() {
		return appConfig;
	}
	
	private String title = null;	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	private boolean visible = false;
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	private I_ModuleHost host;
	public I_ModuleHost getHost() {
		return host;
	}
	
	@Override
	public Callback<Void, Void> getActivationCallback() {
		return new Callback<Void, Void>() {
			@Override
			public Void call(final Void vArg) {
				return onCallBack(vArg);
			}
		};
	}
	
	public Void onCallBack(final Void vArg) {
		appConfig.setCurrentModule(this);
		return null;		
	}

	@Override
	public void hide() {
		setVisible(false);
	}

	@Override
	public void initialize(final I_ModuleHost host) {
		this.host = host;
	}

	@Override
	public void show(final Stage stage) throws Exception {
		setVisible(true);
	}
	
	public void showScene(Stage stage, String fxmlPath) throws IOException {
		NodeWithController nodeWithController = getAppConfig().getFXMLLoader().load(fxmlPath);
		Parent root = (Parent) nodeWithController.node; 
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		ModuleBase other = (ModuleBase) obj;
		if (title == null) {
			if (other.title != null) return false;
		} else if (!title.equals(other.title)) return false;
		return true;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() +" [title=" + title + 
			", visible=" + visible + ", host=" + host + "]";
	}

}
