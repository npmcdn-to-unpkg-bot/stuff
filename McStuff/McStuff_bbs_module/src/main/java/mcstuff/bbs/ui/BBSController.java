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

import java.net.URL;
import java.util.ResourceBundle;

import javax.script.ScriptEngine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.fxml.Initializable;

import mcstuff.modules.BBSModule;

@Component
public class BBSController implements Initializable, I_BBSController {
	
	@Autowired
	private BBSModule bbsModule;
	
	@Autowired
	private ScriptEngine scriptEngine;
	
	private URL locationURL;
	private ResourceBundle resourceBundle;


	@Override
	public void initialize(URL url, ResourceBundle bundle) {
		locationURL = url;
		resourceBundle = bundle;
	}
	
	@Override
	public BBSModule getModule() {
		return bbsModule;
	}

	@Override
	public ScriptEngine getScriptEngine() {
		return scriptEngine;
	}

	public URL getLocationURL() {
		return locationURL;
	}

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	

}
