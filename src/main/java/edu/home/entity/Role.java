package edu.home.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	@Id
	private long id;

	@Column(name="create_by")
	private long createBy;

	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="display_name")
	private String displayName;

	@Column(name="is_display")
	private boolean isDisplay;

	private String name;

	//bi-directional many-to-one association to RolePermission
	@JsonIgnore
	@OneToMany(mappedBy="role")
	private List<RolePermission> rolePermissions;

	//bi-directional many-to-one association to UserRole
	@JsonIgnore
	@OneToMany(mappedBy="role")
	private List<UserRole> userRoles;
}