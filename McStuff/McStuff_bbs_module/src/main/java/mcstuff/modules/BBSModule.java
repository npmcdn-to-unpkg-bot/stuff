package mcstuff.modules;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import mcstuff.api.module.I_Module;
import mcstuff.api.module.I_ModuleHost;
import mcstuff.api.module.ModuleBase;

@Component
@Configuration
@EntityScan(basePackages={"mcstuff.bbs.model.BBSConnectionEntity"})
@EnableJpaRepositories(basePackages={"mcstuff.bbs.model.BBSConnectionRepository"})
public class BBSModule extends ModuleBase implements I_Module {
	
	public BBSModule() {
		setTitle("BBS Module");
	}

	@Override
	public void initialize(I_ModuleHost host) {
		super.initialize(host);
	}
}
