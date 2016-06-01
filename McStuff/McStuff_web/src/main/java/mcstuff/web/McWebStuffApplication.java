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
import mcstuff.web.model.entities.security.AccountEntity;
import mcstuff.web.model.entities.security.AccountRepository;
import mcstuff.web.model.entities.security.PrivilegeEntity;
import mcstuff.web.model.entities.security.PrivilegeRepository;
import mcstuff.web.model.entities.security.RoleEntity;
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
			private PrivilegeEntity createPrivilegeIfNotFound(final E_Privilege privilegeEnum) {
				PrivilegeEntity privilege = privilegeRepository.findByName(privilegeEnum.toString());
				if (privilege == null) {
					privilege = new PrivilegeEntity(privilegeEnum.toString());
					privilegeRepository.save(privilege);
				}
				return privilege;
			}

			@Transactional
			private RoleEntity createRoleIfNotFound(final E_Role roleEnum,
					final Collection<PrivilegeEntity> privileges) {
				RoleEntity role = roleRepository.findByName(roleEnum.toString());
				if (role == null) {
					role = new RoleEntity(roleEnum.toString());
					role.getPrivileges().addAll(privileges);
					roleRepository.save(role);
				}
				return role;
			}

			@Override
			public void run(final String... args) throws Exception {

				final PrivilegeEntity readPrivilege = createPrivilegeIfNotFound(E_Privilege.READ);
				final PrivilegeEntity writePrivilege = createPrivilegeIfNotFound(E_Privilege.WRITE);
				final PrivilegeEntity suggestPrivilege = createPrivilegeIfNotFound(E_Privilege.SUGGEST);
				final List<PrivilegeEntity> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege,
						suggestPrivilege);
				createRoleIfNotFound(E_Role.ROLE_ADMIN, adminPrivileges);
				createRoleIfNotFound(E_Role.ROLE_USER, Arrays.asList(readPrivilege));

				AccountEntity acct = accountRepository.findByUsername("misha");
				if (acct == null) {
					final RoleEntity adminRole = roleRepository.findByName(E_Role.ROLE_ADMIN.toString());
					acct = new AccountEntity("misha", passwordEncoder.encode("masha"), "Misha",
							"michael.cassidy@ldschurch.org");
					acct.getRoles().add(adminRole);
					accountRepository.save(acct);
				}
			}
		};
	}
}
