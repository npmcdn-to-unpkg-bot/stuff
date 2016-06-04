package mcstuff.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mcstuff.web.service.SecurityService;

@RestController("appController")
@RequestMapping(value = "rest/app")
public class AppController {

	@Autowired
	SecurityService securityService;

	@RequestMapping(value = "/auth", method = {
		RequestMethod.GET
	})
	public Authentication getCurrentUser() {
		final Authentication auth = securityService.getCurrentAuth();
		return auth;
	}

}
