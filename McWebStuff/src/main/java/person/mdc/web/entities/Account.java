package person.mdc.web.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name = "Account")
@Table(name = "ACCOUNT")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static class UserDetailsImpl extends User implements UserDetails {
		private static final long serialVersionUID = 1L;

		private Account account;

		public UserDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
			super(username, password, authorities);
			
		}

		public Account getAccount() {
			return account;
		}

		public void setAccount(Account account) {
			this.account = account;
		}
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "USER_NAME", nullable = false, unique = true, length = 50)
	private String username;

	@Column(name = "PASSWORD", length = 300)
	private String password;
	
	@Column(name = "DISPLAY", length = 50)
	private String display;
	
	@Column(name = "EMAIL", length = 200)
	private String email;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( 
        name = "account_roles", 
        joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")) 
    private Collection<Role> roles = new HashSet<>();

	public Account() {
	}
	
	public Account(String username, String password, String display, String email) {
		super();
		this.username = username;
		this.password = password;
		this.display = display;
		this.email = email;
	}
	
	public UserDetailsImpl getUserDetails(Collection<? extends GrantedAuthority> authorities) {
		UserDetailsImpl userDet = new UserDetailsImpl(username, password, authorities);
		userDet.setAccount(this);
		return userDet;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Collection<Role> getRoles() {
		return roles;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((display == null) ? 0 : display.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Account other = (Account) obj;
		if (display == null) {
			if (other.display != null) return false;
		} else if (!display.equals(other.display)) return false;
		if (email == null) {
			if (other.email != null) return false;
		} else if (!email.equals(other.email)) return false;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		if (password == null) {
			if (other.password != null) return false;
		} else if (!password.equals(other.password)) return false;
		if (roles == null) {
			if (other.roles != null) return false;
		} else if (!roles.equals(other.roles)) return false;
		if (username == null) {
			if (other.username != null) return false;
		} else if (!username.equals(other.username)) return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", password=" + password + ", display=" + display
				+ ", email=" + email + ", roles=" + roles + "]";
	}

}
