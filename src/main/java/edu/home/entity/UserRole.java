package edu.home.entity;

import javax.persistence.*;

import java.io.Serializable;


/**
 * The persistent class for the user_roles database table.
 * 
 */
@Entity
@Table(name="user_roles")
public class UserRole implements Serializable {
	@Id
	private long id;

	//bi-directional many-to-one association to Role
	@ManyToOne
	private Role role;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;
}