package edu.home.entity;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_roles database table.
 * 
 */
@Entity
@Table(name="user_roles")
@Data
public class UserRole implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	//bidirectional many-to-one association to Role
	@ManyToOne
	private Role role;

	//bidirectional many-to-one association to User
	@ManyToOne
	private User user;
}