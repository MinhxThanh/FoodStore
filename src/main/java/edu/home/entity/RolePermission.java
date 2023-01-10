package edu.home.entity;

import javax.persistence.*;

import java.io.Serializable;


/**
 * The persistent class for the role_permissions database table.
 * 
 */
@Entity
@Table(name="role_permissions")
@NamedQuery(name="RolePermission.findAll", query="SELECT r FROM RolePermission r")
public class RolePermission implements Serializable {
	@Id
	private long id;

	//bi-directional many-to-one association to Permission
	@ManyToOne
	private Permission permission;

	//bi-directional many-to-one association to Role
	@ManyToOne
	private Role role;
}