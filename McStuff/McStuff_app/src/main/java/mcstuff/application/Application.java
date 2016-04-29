package mcstuff.application;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.spring.AbstractJavaFxApplicationSupport;
import javafx.stage.Stage;
import mcstuff.api.I_ModuleHost;

@Lazy
@SpringBootApplication
public class Application extends AbstractJavaFxApplicationSupport implements I_ModuleHost {
	
	private Stage currentStage;
	
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

		setCurrentStage(stage, true, false);
	}
	
	public static void main(String[] args) {
		launchApp(Application.class, args);
	}

	@Override
	public ApplicationContext getContext() {
		return applicationContext;
	}

	@Override
	public Stage getCurrentStage() {
		return currentStage;
	}

	@Override
	public void setCurrentStage(Stage stage, boolean bShow, boolean bWait) {
		if(currentStage != null) {
			currentStage.hide();
		}
		this.currentStage = stage;
		if(bShow) {
			if(bWait) {
				this.currentStage.showAndWait();
			} else {
				this.currentStage.show();
			}
		}
	}
	
	

}
