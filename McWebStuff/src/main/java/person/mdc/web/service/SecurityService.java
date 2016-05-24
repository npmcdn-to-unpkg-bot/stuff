package person.mdc.web.service;

import javax.annotation.PostConstruct;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import person.mdc.web.model.domain.core.User;

@Service(value = "securityService")
public class SecurityService {
	
	@PostConstruct
	public void init() {
	   // ...
	}
	
	public User getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = new User(auth);
		return currentUser;
	}
}
