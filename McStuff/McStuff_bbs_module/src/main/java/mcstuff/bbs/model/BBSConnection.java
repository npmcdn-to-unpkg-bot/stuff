package mcstuff.bbs.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity(name = "BBSConnection")
@Table(name = "BBS_CONNECTION")
@Access(AccessType.PROPERTY)
public class BBSConnection implements Externalizable {
	
	private LongProperty id = new SimpleLongProperty();
	public final LongProperty idProperty() {
		return this.id;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public final Long getId() {
		return this.idProperty().get();
	}
	public final void setId(final Long id) {
		this.idProperty().set(id);
	}
		
	private StringProperty name = new SimpleStringProperty();
	public final StringProperty nameProperty() {
		return this.name;
	}
	@Column(name = "NAME", nullable= false, unique = true,  length = 100)	
	public final java.lang.String getName() {
		return this.nameProperty().get();
	}
	public final void setName(final java.lang.String name) {
		this.nameProperty().set(name);
	}
	
	private StringProperty description = new SimpleStringProperty();
	public final StringProperty descriptionProperty() {
		return this.description;
	}
	@Column(name = "DESCRIPTION", length = 200)
	public final java.lang.String getDescription() {
		return this.descriptionProperty().get();
	}
	public final void setDescription(final java.lang.String description) {
		this.descriptionProperty().set(description);
	}
	
	private StringProperty serverURL = new SimpleStringProperty();
	public final StringProperty serverURLProperty() {
		return this.serverURL;
	}
	@Column(name = "SERVER_URL", nullable = false, length = 250)
	public final java.lang.String getServerURL() {
		return this.serverURLProperty().get();
	}
	public final void setServerURL(final java.lang.String serverURL) {
		this.serverURLProperty().set(serverURL);
	}
	
	private StringProperty BBSTag = new SimpleStringProperty();
	public final StringProperty BBSTagProperty() {
		return this.BBSTag;
	}
	@Column(name = "BBS_TAG", nullable = false, length = 50)
	public final java.lang.String getBBSTag() {
		return this.BBSTagProperty().get();
	}	
	public final void setBBSTag(final java.lang.String BBSTag) {
		this.BBSTagProperty().set(BBSTag);
	}
		
	private StringProperty userName = new SimpleStringProperty();
	public final StringProperty userNameProperty() {
		return this.userName;
	}
	@Column(name = "USER_NAME", nullable = false, length = 50)
	public final java.lang.String getUserName() {
		return this.userNameProperty().get();
	}
	public final void setUserName(final java.lang.String userName) {
		this.userNameProperty().set(userName);
	}
	
	private StringProperty password = new SimpleStringProperty();
	public final StringProperty passwordProperty() {
		return this.password;
	}
	@Column(name = "PASSWORD", nullable = false, length = 50)
	public final java.lang.String getPassword() {
		return this.passwordProperty().get();
	}
	public final void setPassword(final java.lang.String password) {
		this.passwordProperty().set(password);
	}
	
	
	public BBSConnection() {}

	public BBSConnection(Long id, String name, String description, String serverURL,
			String BBSTag, String userName, String password) {
		super();
		this.id.set(id);
		this.name.set(name);
		this.description.set(description);
		this.serverURL.set(serverURL);
		this.BBSTag.set(BBSTag);
		this.userName.set(userName);
		this.password.set(password);
	}
	
	@Transient 
	public String getBaseURL() {
		return getServerURL() + "/" +getBBSTag();
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeLong(getId());
		out.writeUTF(getName());
		out.writeUTF(getDescription());
		out.writeUTF(getServerURL());
		out.writeUTF(getBBSTag());
		out.writeUTF(getUserName());
		out.writeUTF(getPassword());
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		setId(in.readLong());
		setName(in.readUTF());
		setDescription(in.readUTF());
		setServerURL(in.readUTF());
		setBBSTag(in.readUTF());
		setUserName(in.readUTF());
		setPassword(in.readUTF());
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((BBSTag.get() == null) ? 0 : BBSTag.get().hashCode());
		result = prime * result + ((description.get() == null) ? 0 : description.get().hashCode());
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((name.get() == null) ? 0 : name.get().hashCode());
		result = prime * result + ((password.get() == null) ? 0 : password.get().hashCode());
		result = prime * result + ((serverURL.get() == null) ? 0 : serverURL.get().hashCode());
		result = prime * result + ((userName.get() == null) ? 0 : userName.get().hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		BBSConnection other = (BBSConnection) obj;
		if (BBSTag.get() == null) {
			if (other.BBSTag.get() != null) return false;
		} else if (!BBSTag.get().equals(other.BBSTag.get())) return false;
		if (description.get() == null) {
			if (other.description.get() != null) return false;
		} else if (!description.get().equals(other.description.get())) return false;
		if (getId() == null) {
			if (other.getId() != null) return false;
		} else if (!getId().equals(other.getId())) return false;
		if (name.get() == null) {
			if (other.name.get() != null) return false;
		} else if (!name.get().equals(other.name.get())) return false;
		if (password.get() == null) {
			if (other.password.get() != null) return false;
		} else if (!password.get().equals(other.password.get())) return false;
		if (serverURL.get() == null) {
			if (other.serverURL.get() != null) return false;
		} else if (!serverURL.get().equals(other.serverURL.get())) return false;
		if (userName.get() == null) {
			if (other.userName.get() != null) return false;
		} else if (!userName.get().equals(other.userName.get())) return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "BBSConnection [id=" + getId() + ", name=" + getName() + ", description="
				+ getDescription() + ", serverURL=" + getServerURL() + ", BBSTag=" + getBBSTag()
				+ ", userName=" + getUserName() + ", password=" + getPassword() + "]";
	}
	
	
}
