package person.mdc.web.model.dto.app;

public class WebUserDTO {
	private String userName;
	private String displayName;
	
	public WebUserDTO() {
	}

	public WebUserDTO(String userName, String displayName) {
		super();
		this.userName = userName;
		this.displayName = displayName;
	}

	public String getUserName() {
		return userName;
	}

	public WebUserDTO setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getDisplayName() {
		return displayName;
	}

	public WebUserDTO setDisplayName(String displayName) {
		this.displayName = displayName;
		return this;
	}
	
	
	
}
