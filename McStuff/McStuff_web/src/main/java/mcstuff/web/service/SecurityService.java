package mcstuff.web.service;

import javax.annotation.PostConstruct;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service(value = "securityService")
public class SecurityService {

	public Authentication getCurrentAuth() {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		return auth;
	}

	@PostConstruct
	public void init() {
		// ...
	}
}
