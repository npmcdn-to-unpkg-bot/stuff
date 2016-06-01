package mcstuff.web.model.dto.app;

public class WebUserDTO {
	private String userName;
	private String displayName;

	public WebUserDTO() {
	}

	public WebUserDTO(final String userName, final String displayName) {
		super();
		this.userName = userName;
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getUserName() {
		return userName;
	}

	public WebUserDTO setDisplayName(final String displayName) {
		this.displayName = displayName;
		return this;
	}

	public WebUserDTO setUserName(final String userName) {
		this.userName = userName;
		return this;
	}

}
