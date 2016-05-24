package person.mdc.web.model.domain.core;

import org.springframework.security.core.Authentication;

import person.mdc.web.model.dto.app.WebUserDTO;
import person.mdc.web.model.entities.security.AccountEntity;
import person.mdc.web.model.entities.security.AccountEntity.UserDetailsImpl;

public class User {
	private AccountEntity account;

	public User(Authentication auth) {
		super();
		UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
		this.account = userDetails.getAccount();
	}

	public AccountEntity getAccount() {
		return account;
	}

	public WebUserDTO getWebUserDTO() {
		return new WebUserDTO(getAccount().getUsername(), getAccount().getDisplay());
	}
	
}
