package edu.home.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the permissions database table.
 * 
 */
@Entity
@Table(name="permissions")
@NamedQuery(name="Permission.findAll", query="SELECT p FROM Permission p")
public class Permission implements Serializable {
	@Id
	private long id;

	@Column(name="display_name")
	private String displayName;

	@Column(name="is_display")
	private boolean isDisplay;

	private String name;

	//bi-directional many-to-one association to RolePermission
	@JsonIgnore
	@OneToMany(mappedBy="permission")
	private List<RolePermission> rolePermissions;

	//bi-directional many-to-one association to UserPermission
	@JsonIgnore
	@OneToMany(mappedBy="permission")
	private List<UserPermission> userPermissions;
}