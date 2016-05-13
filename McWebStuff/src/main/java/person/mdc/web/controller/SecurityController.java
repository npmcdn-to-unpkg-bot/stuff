package person.mdc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import person.mdc.web.service.SecurityService;

@RestController("securityController")
@RequestMapping(value = "rest/security")
public class SecurityController {
	
	@Autowired
	SecurityService securityService;

	@RequestMapping(value = "/currentAuth", method = {RequestMethod.GET})
	public Authentication getCurrentAuthentication() {
		return securityService.getCurrentAuthentication();
	}
}
