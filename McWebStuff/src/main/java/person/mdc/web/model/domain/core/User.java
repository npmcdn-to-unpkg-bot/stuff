package person.mdc.web.model.domain.core;

import org.springframework.security.core.Authentication;

import person.mdc.web.model.dto.app.WebUserDTO;
import person.mdc.web.model.entities.security.Account;
import person.mdc.web.model.entities.security.Account.UserDetailsImpl;

public class User {
	private Account account;

	public User(Authentication auth) {
		super();
		UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
		this.account = userDetails.getAccount();
	}

	public Account getAccount() {
		return account;
	}

	public WebUserDTO getWebUser() {
		WebUserDTO userDTO = new WebUserDTO();
		userDTO.setUserName(getAccount().getUsername());
		userDTO.setDisplayName(getAccount().getDisplay());
		return userDTO;
	}
	
}
