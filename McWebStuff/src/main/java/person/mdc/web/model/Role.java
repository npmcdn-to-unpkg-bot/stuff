package person.mdc.web.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

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
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    private String name;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Collection<Account> accounts = new HashSet<>();
 
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "roles_privileges", 
        joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges = new HashSet<>();
    
    public Role() {
	}

	public Role(String name) {
		super();
		this.name = name;
	}

	public Role(String name, Collection<Account> accounts, Collection<Privilege> privileges) {
		super();
		this.name = name;
		this.accounts = accounts;
		this.privileges = privileges;
	}

	public Long getId() {
		return id;
	}

	public Collection<Account> getAccounts() {
		return accounts;
	}

	public Collection<Privilege> getPrivileges() {
		return privileges;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
