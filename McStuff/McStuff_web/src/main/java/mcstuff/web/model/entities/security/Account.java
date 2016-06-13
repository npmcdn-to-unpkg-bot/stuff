/* Copyright (C) 2016+ Michael Cassidy - All Rights Reserved
	This file is a part of McStuff

    McStuff is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    McStuff is distributed as sample code in the hope that it will be 
    useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with McStuff.  If not, see <http://www.gnu.org/licenses/>. 
 */
package mcstuff.web.model.entities.security;

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
	public static class UserDetailsImpl extends User implements UserDetails {
		private static final long serialVersionUID = 1L;

		private Account account;

		public UserDetailsImpl(final String username, final String password,
				final Collection<? extends GrantedAuthority> authorities) {
			super(username, password, authorities);

		}

		public Account getAccount() {
			return account;
		}

		public void setAccount(final Account account) {
			this.account = account;
		}

	}

	private static final long serialVersionUID = 1L;

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
	@JoinTable(name = "account_roles", joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private final Collection<Role> roles = new HashSet<>();

	public Account() {
	}

	public Account(final String username, final String password, final String display, final String email) {
		super();
		this.username = username;
		this.password = password;
		this.display = display;
		this.email = email;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Account other = (Account) obj;
		if (display == null) {
			if (other.display != null) {
				return false;
			}
		} else if (!display.equals(other.display)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (roles == null) {
			if (other.roles != null) {
				return false;
			}
		} else if (!roles.equals(other.roles)) {
			return false;
		}
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		return true;
	}

	public String getDisplay() {
		return display;
	}

	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public UserDetailsImpl getUserDetails(final Collection<? extends GrantedAuthority> authorities) {
		final UserDetailsImpl userDet = new UserDetailsImpl(username, password, authorities);
		userDet.setAccount(this);
		return userDet;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (display == null ? 0 : display.hashCode());
		result = prime * result + (email == null ? 0 : email.hashCode());
		result = prime * result + (id == null ? 0 : id.hashCode());
		result = prime * result + (password == null ? 0 : password.hashCode());
		result = prime * result + (roles == null ? 0 : roles.hashCode());
		result = prime * result + (username == null ? 0 : username.hashCode());
		return result;
	}

	public void setDisplay(final String display) {
		this.display = display;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", password=" + password + ", display=" + display
				+ ", email=" + email + ", roles=" + roles + "]";
	}

}
