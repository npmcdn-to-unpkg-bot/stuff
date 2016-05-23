package person.mdc.web.model.domain.core;

import org.springframework.security.core.Authentication;

public class User {
	private Authentication authentication;

	public User(Authentication authentication) {
		super();
		this.authentication = authentication;
	}

	public Authentication getAuthentication() {
		return authentication;
	}
	
}
