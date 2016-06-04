package mcstuff.bbs.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "BBSConnection")
@Table(name = "BBS_CONNECTION")
public class BBSConnection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME", nullable= false, unique = true,  length = 100)
	private String name;
	
	@Column(name = "DESCRIPTION", length = 200)
	private String description;
	
	@Column(name = "SERVER_URL", nullable = false, length = 250)
	private String serverUrl;
	
	@Column(name = "BBS_TAG", nullable = false, length = 50)
	private String bbsTag;
	
	@Column(name = "USER_NAME", nullable = false, length = 50)
	private String userName;
	
	@Column(name = "PASSWORD", nullable = false, length = 50)
	private String password;

	public BBSConnection() {
		
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public String getBBSTag() {
		return bbsTag;
	}

	public void setBBSTag(String bbsTag) {
		this.bbsTag = bbsTag;
	}

	public String getBaseUrl() {
		return serverUrl + "/" +bbsTag;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
