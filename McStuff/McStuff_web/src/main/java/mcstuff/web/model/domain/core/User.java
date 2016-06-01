package mcstuff.web.model.domain.core;

import org.springframework.security.core.Authentication;

import mcstuff.web.model.dto.app.WebUserDTO;
import mcstuff.web.model.entities.security.AccountEntity;
import mcstuff.web.model.entities.security.AccountEntity.UserDetailsImpl;

public class User {
	private final AccountEntity account;

	public User(final Authentication auth) {
		super();
		final UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
		account = userDetails.getAccount();
	}

	public AccountEntity getAccount() {
		return account;
	}

	public WebUserDTO getWebUserDTO() {
		return new WebUserDTO(getAccount().getUsername(), getAccount().getDisplay());
	}

}
