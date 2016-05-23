package person.mdc.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import person.mdc.web.model.entities.security.Account;
import person.mdc.web.model.entities.security.AccountRepository;
import person.mdc.web.model.entities.security.Privilege;
import person.mdc.web.model.entities.security.Role;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	public static enum E_Role {
		ROLE_USER,
		ROLE_ADMIN,
		ROLE_EDITOR,
	}
	
	public static enum E_Privilege {
		READ,
		WRITE,
		SUGGEST,
	}

	@Autowired
	AccountRepository accountRepository;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	protected UserDetailsService userDetailsService() {
		
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				Account account = accountRepository.findByUsername(username);
				if (account != null) {
					UserDetails details = account.getUserDetails(getAuthorities(account.getRoles()));
					return details;
				} else {
					throw new UsernameNotFoundException("could not find the user '" + username + "'");
				}
			}

			private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
				return getGrantedAuthorities(getPrivileges(roles));
			}

			private List<String> getPrivileges(Collection<Role> roles) {
				List<String> privileges = new ArrayList<>();
				List<Privilege> collection = new ArrayList<>();
				for (Role role : roles) {
					collection.addAll(role.getPrivileges());
				}
				for (Privilege item : collection) {
					privileges.add(item.getName());
				}
				return privileges;
			}

			private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
				List<GrantedAuthority> authorities = new ArrayList<>();
				for (String privilege : privileges) {
					authorities.add(new SimpleGrantedAuthority(privilege));
				}
				return authorities;
			}

		};
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().fullyAuthenticated()
			.and().httpBasic()
			.and().formLogin().permitAll()
			.and().logout().permitAll()
			.and().anonymous().disable();
		http.csrf().disable();
		
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

}
