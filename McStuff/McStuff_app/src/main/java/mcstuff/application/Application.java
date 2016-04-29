package mcstuff.application;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.spring.AbstractJavaFxApplicationSupport;
import javafx.stage.Stage;

@Lazy
@SpringBootApplication
public class Application extends AbstractJavaFxApplicationSupport {
	
	@Bean
	@Primary
	public DataSource dataSource() {
	    return (DataSource) DataSourceBuilder
	        .create()
	        .username("dbUser")
	        .password("dbPass")
	        .url("jdbc:derby:mcstuff_db;create=true")
	        .build();
	}
	
	@Value("${app.ui.title:McStuff}")//
	private String windowTitle;

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle(windowTitle);
		stage.setScene(new Scene(new BorderPane()));
		stage.setResizable(true);
		stage.centerOnScreen();
		stage.show();
	}
	
	public static void main(String[] args) {
		launchApp(Application.class, args);
	}

}
