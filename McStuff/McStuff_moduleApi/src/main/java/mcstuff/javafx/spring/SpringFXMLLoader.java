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
