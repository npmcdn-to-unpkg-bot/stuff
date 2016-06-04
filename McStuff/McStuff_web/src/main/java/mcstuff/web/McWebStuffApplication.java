package mcstuff.web;

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

import mcstuff.web.WebSecurityConfig.E_Privilege;
import mcstuff.web.WebSecurityConfig.E_Role;
import mcstuff.web.model.entities.security.Account;
import mcstuff.web.model.entities.security.AccountRepository;
import mcstuff.web.model.entities.security.Privilege;
import mcstuff.web.model.entities.security.PrivilegeRepository;
import mcstuff.web.model.entities.security.Role;
import mcstuff.web.model.entities.security.RoleRepository;

@SpringBootApplication
public class McWebStuffApplication {
	public static void main(final String[] args) {
		SpringApplication.run(McWebStuffApplication.class, args);
	}

	@Bean
	@Primary
	public DataSource dataSource() {
		return DataSourceBuilder.create().username("dbUser").password("dbPass")
				.url("jdbc:derby:mcwebstuff_db;create=true").build();
	}

	@Bean
	CommandLineRunner init(final AccountRepository accountRepository, final RoleRepository roleRepository,
			final PrivilegeRepository privilegeRepository, final PasswordEncoder passwordEncoder) {
		return new CommandLineRunner() {
			@Transactional
			private Privilege createPrivilegeIfNotFound(final E_Privilege privilegeEnum) {
				Privilege privilege = privilegeRepository.findByName(privilegeEnum.toString());
				if (privilege == null) {
					privilege = new Privilege(privilegeEnum.toString());
					privilegeRepository.save(privilege);
				}
				return privilege;
			}

			@Transactional
			private Role createRoleIfNotFound(final E_Role roleEnum,
					final Collection<Privilege> privileges) {
				Role role = roleRepository.findByName(roleEnum.toString());
				if (role == null) {
					role = new Role(roleEnum.toString());
					role.getPrivileges().addAll(privileges);
					roleRepository.save(role);
				}
				return role;
			}

			@Override
			public void run(final String... args) throws Exception {

				final Privilege readPrivilege = createPrivilegeIfNotFound(E_Privilege.READ);
				final Privilege writePrivilege = createPrivilegeIfNotFound(E_Privilege.WRITE);
				final Privilege suggestPrivilege = createPrivilegeIfNotFound(E_Privilege.SUGGEST);
				final List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege,
						suggestPrivilege);
				createRoleIfNotFound(E_Role.ROLE_ADMIN, adminPrivileges);
				createRoleIfNotFound(E_Role.ROLE_USER, Arrays.asList(readPrivilege));

				Account acct = accountRepository.findByUsername("misha");
				if (acct == null) {
					final Role adminRole = roleRepository.findByName(E_Role.ROLE_ADMIN.toString());
					acct = new Account("misha", passwordEncoder.encode("masha"), "Misha",
							"michael.cassidy@ldschurch.org");
					acct.getRoles().add(adminRole);
					accountRepository.save(acct);
				}
			}
		};
	}
}
