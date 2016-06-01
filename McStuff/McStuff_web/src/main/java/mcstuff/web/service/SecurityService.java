package mcstuff.web.service;

import javax.annotation.PostConstruct;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import mcstuff.web.model.domain.core.User;

@Service(value = "securityService")
public class SecurityService {

	public User getCurrentUser() {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final User currentUser = new User(auth);
		return currentUser;
	}

	@PostConstruct
	public void init() {
		// ...
	}
}
