package person.mdc.web.service;

import javax.annotation.PostConstruct;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service(value = "securityService")
public class SecurityService {
	
	@PostConstruct
	public void init() {
	   // ...
	}
	
	public Authentication getCurrentAuthentication() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth;
	}
}
