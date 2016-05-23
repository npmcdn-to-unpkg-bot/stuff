package person.mdc.web.model.dto.app;

import person.mdc.web.model.domain.core.User;
import person.mdc.web.model.entities.security.Account.UserDetailsImpl;

public class WebUserDTO {
	public String name;

	public WebUserDTO(User user) {
		UserDetailsImpl principal = (UserDetailsImpl) user.getAuthentication().getPrincipal();
		this.name = principal.getAccount().getDisplay();
	}
	
	
}
