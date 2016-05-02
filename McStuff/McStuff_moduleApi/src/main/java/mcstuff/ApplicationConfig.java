package mcstuff;

import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javafx.stage.Stage;
import mcstuff.api.I_ModuleHost;
import mcstuff.api.module.I_Module;
import mcstuff.javafx.spring.SpringFXMLLoader;

@Component
@Configuration
public class ApplicationConfig {

	@Autowired
	SpringFXMLLoader fxmlLoader;

	@Bean
	@Primary
	public DataSource dataSource() {
		return (DataSource) DataSourceBuilder.create().username("dbUser").password("dbPass")
				.url("jdbc:derby:mcstuff_db;create=true").build();
	}

	// application properties
	@Value("${app.ui.title:McStuff}") //
	private String appTitle;

	private I_ModuleHost moduleHost;
	private Stage currentStage;
	private Set<I_Module> modules = new HashSet<>();
	private I_Module currentModule;

	public Stage getCurrentStage() {
		return currentStage;
	}

	public void setCurrentStage(Stage currentStage) {
		if (this.currentStage != null) {
			this.currentStage.hide();
		}
		this.currentStage = currentStage;
	}

	public I_ModuleHost getModuleHost() {
		return moduleHost;
	}

	public void setModuleHost(I_ModuleHost moduleHost) {
		this.moduleHost = moduleHost;
	}

	public I_Module getCurrentModule() {
		return currentModule;
	}

	public void setCurrentModule(I_Module currentModule) {
		if (this.currentModule != null) {
			this.currentModule.hide();
		}
		this.currentModule = currentModule;
		if (this.currentModule != null) {
			this.currentModule.show(currentStage);
		} else {
			moduleHost.showDefaultView();
		}

	}

	public Set<I_Module> getModules() {
		return modules;
	}

	public String getAppTitle() {
		return appTitle;
	}

	public SpringFXMLLoader getFXMLLoader() {
		return fxmlLoader;
	}

}
