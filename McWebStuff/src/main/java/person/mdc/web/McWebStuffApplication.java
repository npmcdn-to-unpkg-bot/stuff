package person.mdc.web;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import person.mdc.web.WebSecurityConfig.E_Privilege;
import person.mdc.web.WebSecurityConfig.E_Role;
import person.mdc.web.model.entities.security.AccountEntity;
import person.mdc.web.model.entities.security.AccountRepository;
import person.mdc.web.model.entities.security.PrivilegeEntity;
import person.mdc.web.model.entities.security.PrivilegeRepository;
import person.mdc.web.model.entities.security.RoleEntity;
import person.mdc.web.model.entities.security.RoleRepository;

@SpringBootApplication
public class McWebStuffApplication {
	@Bean
	@Primary
	public DataSource dataSource() {
		return DataSourceBuilder.create().username("dbUser").password("dbPass")
				.url("jdbc:derby:mcwebstuff_db;create=true").build();
	}

	@Bean
	CommandLineRunner init(final AccountRepository accountRepository, RoleRepository roleRepository,
			PrivilegeRepository privilegeRepository, final PasswordEncoder passwordEncoder) {
		return new CommandLineRunner() {
			@Override
			public void run(String... arg0) throws Exception {
				
				PrivilegeEntity readPrivilege = createPrivilegeIfNotFound(E_Privilege.READ);
		        PrivilegeEntity writePrivilege = createPrivilegeIfNotFound(E_Privilege.WRITE);
		        PrivilegeEntity suggestPrivilege = createPrivilegeIfNotFound(E_Privilege.SUGGEST);
		        List<PrivilegeEntity> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege, suggestPrivilege);        
		        createRoleIfNotFound(E_Role.ROLE_ADMIN, adminPrivileges);
		        createRoleIfNotFound(E_Role.ROLE_USER, Arrays.asList(readPrivilege));
		 
		        AccountEntity acct = accountRepository.findByUsername("misha");
				if(acct == null) {
			        RoleEntity adminRole = roleRepository.findByName(E_Role.ROLE_ADMIN.toString());
					acct = new AccountEntity("misha", passwordEncoder.encode("masha"), "Misha", "michael.cassidy@ldschurch.org");
					acct.getRoles().add(adminRole);
					accountRepository.save(acct);
				}
			}
			
			@Transactional
		    private PrivilegeEntity createPrivilegeIfNotFound(E_Privilege privilegeEnum) {
		        PrivilegeEntity privilege = privilegeRepository.findByName(privilegeEnum.toString());
		        if (privilege == null) {
		            privilege = new PrivilegeEntity(privilegeEnum.toString());
		            privilegeRepository.save(privilege);
		        }
		        return privilege;
		    }
		 
		    @Transactional
		    private RoleEntity createRoleIfNotFound(E_Role roleEnum, Collection<PrivilegeEntity> privileges) {
		        RoleEntity role = roleRepository.findByName(roleEnum.toString());
		        if (role == null) {
		            role = new RoleEntity(roleEnum.toString());
		            role.getPrivileges().addAll(privileges);
		            roleRepository.save(role);
		        }
		        return role;
		    }
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(McWebStuffApplication.class, args);
	}
}
