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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="VS_Roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Role_Id")
	private long roleId;
   

	@Column(name="Role_Name",nullable = false)
	private String roleName;
	
	 @ManyToMany(mappedBy = "roles")
	    private List<User> users;
	 
	 public Role(String role) {
	        this.roleName = role;
	    }
}
