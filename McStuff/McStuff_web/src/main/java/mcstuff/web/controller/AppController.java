package mcstuff.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mcstuff.web.model.domain.core.User;
import mcstuff.web.model.dto.app.WebUserDTO;
import mcstuff.web.service.SecurityService;

@RestController("appController")
@RequestMapping(value = "rest/app")
public class AppController {

	@Autowired
	SecurityService securityService;

	@RequestMapping(value = "/currentUser", method = {
		RequestMethod.GET
	})
	public WebUserDTO getCurrentUser() {
		final User currentUser = securityService.getCurrentUser();
		return currentUser.getWebUserDTO();
	}

}
