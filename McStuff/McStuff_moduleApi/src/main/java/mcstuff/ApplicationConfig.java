package mcstuff;

import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javafx.stage.Stage;

import mcstuff.api.module.I_Module;
import mcstuff.api.module.I_ModuleHost;
import mcstuff.javafx.spring.SpringFXMLLoader;

@Component
@Configuration
public class ApplicationConfig {

	@Autowired
	private SpringFXMLLoader fxmlLoader;

	@Autowired
	private ApplicationContext applicationContext;

	// application properties
	@Value("${app.ui.title:McStuff}") //
	private String appTitle;

	private I_ModuleHost moduleHost;
	private Stage currentStage;

	private final Set<I_Module> modules = new HashSet<>();
	private I_Module currentModule;

	@Bean
	@Primary
	public DataSource dataSource() {
		return DataSourceBuilder.create().username("dbUser").password("dbPass").url("jdbc:derby:mcstuff_db;create=true")
				.build();
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ApplicationConfig other = (ApplicationConfig) obj;
		if (appTitle == null) {
			if (other.appTitle != null) {
				return false;
			}
		} else if (!appTitle.equals(other.appTitle)) {
			return false;
		}
		if (applicationContext == null) {
			if (other.applicationContext != null) {
				return false;
			}
		} else if (!applicationContext.equals(other.applicationContext)) {
			return false;
		}
		if (currentModule == null) {
			if (other.currentModule != null) {
				return false;
			}
		} else if (!currentModule.equals(other.currentModule)) {
			return false;
		}
		if (currentStage == null) {
			if (other.currentStage != null) {
				return false;
			}
		} else if (!currentStage.equals(other.currentStage)) {
			return false;
		}
		if (fxmlLoader == null) {
			if (other.fxmlLoader != null) {
				return false;
			}
		} else if (!fxmlLoader.equals(other.fxmlLoader)) {
			return false;
		}
		if (moduleHost == null) {
			if (other.moduleHost != null) {
				return false;
			}
		} else if (!moduleHost.equals(other.moduleHost)) {
			return false;
		}
		if (modules == null) {
			if (other.modules != null) {
				return false;
			}
		} else if (!modules.equals(other.modules)) {
			return false;
		}
		return true;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public String getAppTitle() {
		return appTitle;
	}

	public I_Module getCurrentModule() {
		return currentModule;
	}

	public Stage getCurrentStage() {
		return currentStage;
	}

	public SpringFXMLLoader getFXMLLoader() {
		return fxmlLoader;
	}

	public I_ModuleHost getModuleHost() {
		return moduleHost;
	}

	public Set<I_Module> getModules() {
		return modules;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (appTitle == null ? 0 : appTitle.hashCode());
		result = prime * result + (applicationContext == null ? 0 : applicationContext.hashCode());
		result = prime * result + (currentModule == null ? 0 : currentModule.hashCode());
		result = prime * result + (currentStage == null ? 0 : currentStage.hashCode());
		result = prime * result + (fxmlLoader == null ? 0 : fxmlLoader.hashCode());
		result = prime * result + (moduleHost == null ? 0 : moduleHost.hashCode());
		result = prime * result + (modules == null ? 0 : modules.hashCode());
		return result;
	}

	public void setCurrentModule(final I_Module currentModule) {
		if (this.currentModule != null) {
			this.currentModule.hide();
		}
		this.currentModule = currentModule;
		if (this.currentModule != null) {
			moduleHost.showModule(this.currentModule);
		} else {
			moduleHost.showDefaultView();
		}

	}

	public void setCurrentStage(final Stage currentStage) {
		if (this.currentStage != null) {
			this.currentStage.hide();
		}
		this.currentStage = currentStage;
	}

	public void setModuleHost(final I_ModuleHost moduleHost) {
		this.moduleHost = moduleHost;
	}

	@Override
	public String toString() {
		return "ApplicationConfig [fxmlLoader=" + fxmlLoader + ", appTitle=" + appTitle + ", moduleHost=" + moduleHost
				+ ", currentStage=" + currentStage + ", modules=" + modules + ", currentModule=" + currentModule + "]";
	}

}
