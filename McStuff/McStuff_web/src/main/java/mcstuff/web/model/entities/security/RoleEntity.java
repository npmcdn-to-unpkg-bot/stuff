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

@Entity(name = "Role")
@Table(name = "ROLE")
public class RoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "roles_privileges", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
	private Collection<PrivilegeEntity> privileges = new HashSet<>();

	public RoleEntity() {
	}

	public RoleEntity(final String name) {
		super();
		this.name = name;
	}

	public RoleEntity(final String name, final Collection<PrivilegeEntity> privileges) {
		super();
		this.name = name;
		this.privileges = privileges;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Collection<PrivilegeEntity> getPrivileges() {
		return privileges;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
