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
package mcstuff.javafx.spring;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javafx.fxml.FXMLLoader;
import javafx.util.Callback;

@Component("defaultFXMLLoader")
public class SpringFXMLLoader {

	@Autowired
	ApplicationContext context;

	public NodeWithController load(final String url) throws IOException {
		final FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
		loader.setControllerFactory(new Callback<Class<?>, Object>() {
			@Override
			public Object call(final Class<?> aClass) {
				return controllerFactory(aClass);
			}
		});
		NodeWithController ret = new NodeWithController();
		ret.node = loader.load();
		ret.controller = loader.getController();
		return ret;
	}

	protected Object controllerFactory(final Class<?> aClass) {
		return context.getBean(aClass);
	}

	public static class NodeWithController {
		public Object node;
		public Object controller;
	}
}
