package edu.home.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@Table(name="roles")
@Data
public class Role implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name="create_date")
	@Temporal(TemporalType.DATE)
	private Date createDate;

	@Column(name="display_name")
	private String displayName;

	@Column(name="is_display")
	private boolean isDisplay;

	private String name;

	//bidirectional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="create_by")
	private User user;

	//bidirectional many-to-one association to UserRole
	@JsonIgnore
	@OneToMany(mappedBy="role")
	private List<UserRole> userRoles;
}