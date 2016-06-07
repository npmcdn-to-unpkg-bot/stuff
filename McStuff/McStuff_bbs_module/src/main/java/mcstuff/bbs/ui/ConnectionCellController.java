package mcstuff.bbs.ui;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import mcstuff.bbs.model.BBSConnection;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ConnectionCellController implements Initializable {
	
	private ObjectProperty<BBSConnection> connection = new SimpleObjectProperty<>();

	@FXML Label lblName;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	
	public final ObjectProperty<BBSConnection> connectionProperty() {
		return this.connection;
	}
	public final mcstuff.bbs.model.BBSConnection getConnection() {
		return this.connectionProperty().get();
	}
	public final void setConnection(final mcstuff.bbs.model.BBSConnection connection) {
		this.connectionProperty().set(connection);
	}
	
	
	
}
