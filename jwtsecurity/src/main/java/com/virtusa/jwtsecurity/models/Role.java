package com.virtusa.jwtsecurity.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name="VS_Roles")
public class Role  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Role_Id")
	private long roleId;
	
	@Column(name="Role_Name",nullable = false)
	private String roleName;
	
	 @ManyToMany(mappedBy = "roles")
	    private List<User> users;
	 
	 public Role() {
	    }

	    public Role(String role) {
	        this.roleName = role;
	    }
	

	public String getRoleName() {
			return roleName;
		}

		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
