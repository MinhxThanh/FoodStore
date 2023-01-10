package edu.home.entity;

import javax.persistence.*;

import java.io.Serializable;


/**
 * The persistent class for the user_permissions database table.
 * 
 */
@Entity
@Table(name="user_permissions")
@NamedQuery(name="UserPermission.findAll", query="SELECT u FROM UserPermission u")
public class UserPermission implements Serializable {
	@Id
	private long id;

	//bi-directional many-to-one association to Permission
	@ManyToOne
	private Permission permission;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

}