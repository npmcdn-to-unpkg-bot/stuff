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
package mcstuff.web;

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

import mcstuff.web.model.entities.security.Account;
import mcstuff.web.model.entities.security.AccountRepository;
import mcstuff.web.model.entities.security.Privilege;
import mcstuff.web.model.entities.security.Role;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	public static enum E_Privilege {
		READ, WRITE
	}

	public static enum E_Role {
		ROLE_USER, ROLE_ADMIN, ROLE_EDITOR,
	}

	@Autowired
	AccountRepository accountRepository;

	@Bean
	public DaoAuthenticationProvider authProvider() {
		final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Override
	public void configure(final AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic().and().formLogin().permitAll().and()
				.logout().permitAll().and().anonymous().disable();
		http.csrf().disable();

	}

	@Override
	public void configure(final WebSecurity web) throws Exception {
		super.configure(web);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {

		return new UserDetailsService() {
			private Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
				return getGrantedAuthorities(getPrivileges(roles));
			}

			private List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
				final List<GrantedAuthority> authorities = new ArrayList<>();
				for (final String privilege : privileges) {
					authorities.add(new SimpleGrantedAuthority(privilege));
				}
				return authorities;
			}

			private List<String> getPrivileges(final Collection<Role> roles) {
				final List<String> privileges = new ArrayList<>();
				final List<Privilege> collection = new ArrayList<>();
				for (final Role role : roles) {
					collection.addAll(role.getPrivileges());
				}
				for (final Privilege item : collection) {
					privileges.add(item.getName());
				}
				return privileges;
			}

			@Override
			public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
				final Account account = accountRepository.findByUsername(username);
				if (account != null) {
					final UserDetails details = account.getUserDetails(getAuthorities(account.getRoles()));
					return details;
				} else {
					throw new UsernameNotFoundException("could not find the user '" + username + "'");
				}
			}

		};
	}

}
