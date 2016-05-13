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
import person.mdc.web.model.Account;
import person.mdc.web.model.AccountRepository;
import person.mdc.web.model.Privilege;
import person.mdc.web.model.PrivilegeRepository;
import person.mdc.web.model.Role;
import person.mdc.web.model.RoleRepository;

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
				
				Privilege readPrivilege = createPrivilegeIfNotFound(E_Privilege.READ);
		        Privilege writePrivilege = createPrivilegeIfNotFound(E_Privilege.WRITE);
		        Privilege suggestPrivilege = createPrivilegeIfNotFound(E_Privilege.SUGGEST);
		        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege, suggestPrivilege);        
		        createRoleIfNotFound(E_Role.ROLE_ADMIN, adminPrivileges);
		        createRoleIfNotFound(E_Role.ROLE_USER, Arrays.asList(readPrivilege));
		 
		        Account acct = accountRepository.findByUsername("misha");
				if(acct == null) {
			        Role adminRole = roleRepository.findByName(E_Role.ROLE_ADMIN.toString());
					acct = new Account("misha", passwordEncoder.encode("masha"), "Misha", "michael.cassidy@ldschurch.org");
					acct.getRoles().add(adminRole);
					accountRepository.save(acct);
				}
			}
			
			@Transactional
		    private Privilege createPrivilegeIfNotFound(E_Privilege privilegeEnum) {
		        Privilege privilege = privilegeRepository.findByName(privilegeEnum.toString());
		        if (privilege == null) {
		            privilege = new Privilege(privilegeEnum.toString());
		            privilegeRepository.save(privilege);
		        }
		        return privilege;
		    }
		 
		    @Transactional
		    private Role createRoleIfNotFound(E_Role roleEnum, Collection<Privilege> privileges) {
		        Role role = roleRepository.findByName(roleEnum.toString());
		        if (role == null) {
		            role = new Role(roleEnum.toString());
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
