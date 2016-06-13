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

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity(name = "BBSConnection")
@Table(name = "BBS_CONNECTION")
@Access(AccessType.PROPERTY)
public class BBSConnection implements Externalizable {
		
	private LongProperty id = new SimpleLongProperty();
	public LongProperty idProperty() {
		return this.id;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return this.idProperty().get();
	}
	public void setId(Long id) {
		this.idProperty().set(id);
	}
		
	private StringProperty name = new SimpleStringProperty();
	public  StringProperty nameProperty() {
		return this.name;
	}
	@Column(name = "NAME", nullable= false, unique = true,  length = 100)	
	public  java.lang.String getName() {
		return this.nameProperty().get();
	}
	public  void setName( java.lang.String name) {
		this.nameProperty().set(name);
	}
	
	private StringProperty description = new SimpleStringProperty();
	public  StringProperty descriptionProperty() {
		return this.description;
	}
	@Column(name = "DESCRIPTION", length = 200)
	public  java.lang.String getDescription() {
		return this.descriptionProperty().get();
	}
	public  void setDescription( java.lang.String description) {
		this.descriptionProperty().set(description);
	}
	
	private StringProperty serverURL = new SimpleStringProperty();
	public  StringProperty serverURLProperty() {
		return this.serverURL;
	}
	@Column(name = "SERVER_URL", nullable = false, length = 250)
	public  java.lang.String getServerURL() {
		return this.serverURLProperty().get();
	}
	public  void setServerURL( java.lang.String serverURL) {
		this.serverURLProperty().set(serverURL);
	}
	
	private StringProperty bbsTag = new SimpleStringProperty();
	public  StringProperty bbsTagProperty() {
		return this.bbsTag;
	}
	
	public  java.lang.String getBbsTag() {
		return this.bbsTagProperty().get();
	}
	
	public  void setBbsTag( java.lang.String bbsTag) {
		this.bbsTagProperty().set(bbsTag);
	}
		
	private StringProperty userName = new SimpleStringProperty();
	public  StringProperty userNameProperty() {
		return this.userName;
	}
	@Column(name = "USER_NAME", nullable = false, length = 50)
	public  java.lang.String getUserName() {
		return this.userNameProperty().get();
	}
	public  void setUserName( java.lang.String userName) {
		this.userNameProperty().set(userName);
	}
	
	private StringProperty password = new SimpleStringProperty();
	public  StringProperty passwordProperty() {
		return this.password;
	}
	@Column(name = "PASSWORD", nullable = false, length = 50)
	public  java.lang.String getPassword() {
		return this.passwordProperty().get();
	}
	public  void setPassword( java.lang.String password) {
		this.passwordProperty().set(password);
	}
	
	private BooleanProperty dirty = new SimpleBooleanProperty();
	
	public  BooleanProperty dirtyProperty() {
		return this.dirty;
	}
	@Transient
	public  boolean isDirty() {
		return this.dirtyProperty().get();
	}
	public  void setDirty( boolean dirty) {
		this.dirtyProperty().set(dirty);
	}
	
	public BBSConnection() {}

	public BBSConnection(Long id, String name, String description, String serverURL,
			String BBSTag, String userName, String password) {
		super();
		this.id.set(id);
		this.name.set(name);
		this.description.set(description);
		this.serverURL.set(serverURL);
		this.bbsTag.set(BBSTag);
		this.userName.set(userName);
		this.password.set(password);
	}
	
	@Transient 
	public String getBaseURL() {
		return getServerURL() + "/" +getBbsTag();
	}
	
	protected InvalidationListener dirtyListener = new InvalidationListener() {
		@Override
		public void invalidated(Observable observable) {
			dirty.set(true);
		}
	};
	
	public void trackChanges(boolean bTrackChanges) {
		if(bTrackChanges) {
			id.addListener(dirtyListener);
			name.addListener(dirtyListener);
			description.addListener(dirtyListener);
			serverURL.addListener(dirtyListener);
			bbsTag.addListener(dirtyListener);
			userName.addListener(dirtyListener);
			password.addListener(dirtyListener);
		} else {
			id.removeListener(dirtyListener);
			name.removeListener(dirtyListener);
			description.removeListener(dirtyListener);
			serverURL.removeListener(dirtyListener);
			bbsTag.removeListener(dirtyListener);
			userName.removeListener(dirtyListener);
			password.removeListener(dirtyListener);
		}
	}

	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeLong(getId());
		out.writeUTF(getName());
		out.writeUTF(getDescription());
		out.writeUTF(getServerURL());
		out.writeUTF(getBbsTag());
		out.writeUTF(getUserName());
		out.writeUTF(getPassword());
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		setId(in.readLong());
		setName(in.readUTF());
		setDescription(in.readUTF());
		setServerURL(in.readUTF());
		setBbsTag(in.readUTF());
		setUserName(in.readUTF());
		setPassword(in.readUTF());
	}
	@Override
	public int hashCode() {
		 int prime = 31;
		int result = 1;
		result = prime * result + ((bbsTag.get() == null) ? 0 : bbsTag.get().hashCode());
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
		if (bbsTag.get() == null) {
			if (other.bbsTag.get() != null) return false;
		} else if (!bbsTag.get().equals(other.bbsTag.get())) return false;
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
				+ getDescription() + ", serverURL=" + getServerURL() + ", BBSTag=" + getBbsTag()
				+ ", userName=" + getUserName() + ", password=" + getPassword() + "]";
	}
	
}
