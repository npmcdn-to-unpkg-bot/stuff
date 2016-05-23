package person.mdc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import person.mdc.web.model.domain.core.User;
import person.mdc.web.model.dto.app.WebUserDTO;
import person.mdc.web.service.SecurityService;

@RestController("appController")
@RequestMapping(value = "rest/app")
public class AppController {
	
	@Autowired
	SecurityService securityService;
	
	@RequestMapping(value = "/currentUser", method = {RequestMethod.GET})
	public WebUserDTO getCurrentUser() {
		Authentication auth = securityService.getCurrentAuthentication();
		User currentUser = new User(auth);
		return new WebUserDTO(currentUser);
	}

}
