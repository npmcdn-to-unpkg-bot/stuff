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
package mcstuff;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javafx.stage.Stage;

import mcstuff.api.module.I_Module;
import mcstuff.api.module.I_ModuleHost;
import mcstuff.javafx.spring.SpringFXMLLoader;

@Configuration
public class ApplicationConfig {
	
	@Bean
	@Primary
	public DataSource dataSource() {
		return DataSourceBuilder.create().username("dbUser").password("dbPass").url("jdbc:derby:mcstuff_db;create=true")
				.build();
	}
	
	@Autowired
	private Environment env;

	@Autowired
	private SpringFXMLLoader defaultFXMLLoader;

	@Autowired
	private ApplicationContext applicationContext;

	// application properties
	@Value("${app.ui.title:McStuff}") //
	private String appTitle;

	private I_ModuleHost moduleHost;
	private Stage currentStage;

	private final Set<I_Module> modules = new HashSet<>();
	private I_Module currentModule;
		
	private Map<String, Object> settings = new HashMap<>();
	public Map<String, Object> getSettings() {
		return settings;
	}
	
	public ApplicationConfig() {
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	public Environment getEnvironment() {
		return env;
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
		return defaultFXMLLoader;
	}

	public I_ModuleHost getModuleHost() {
		return moduleHost;
	}

	public Set<I_Module> getModules() {
		return modules;
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
		return "ApplicationConfig [env=" + env + ", defaultFXMLLoader=" + defaultFXMLLoader + ", applicationContext="
				+ applicationContext + ", appTitle=" + appTitle + ", moduleHost=" + moduleHost + ", currentStage="
				+ currentStage + ", modules=" + modules + ", currentModule=" + currentModule + ", settings=" + settings
				+ "]";
	}


}
