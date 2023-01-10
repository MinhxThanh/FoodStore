package edu.home.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String address;

	private String avatar;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	private String code;

	@Column(name="create_by")
	private long createBy;

	@Column(name="create_date")
	private Timestamp createDate;

	private String email;

	private String fullname;

	private boolean gender;

	@Column(name="is_display")
	private boolean isDisplay;

	private String password;

	private String phone;

	@Column(name="remember_token")
	private String rememberToken;

	private long status;

	private String username;

	//bi-directional many-to-one association to Blog
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Blog> blogs;

	//bi-directional many-to-one association to Category
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Category> categories;

	//bi-directional many-to-one association to Coupon
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Coupon> coupons;

	//bi-directional many-to-one association to Discount
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Discount> discounts;

	//bi-directional many-to-one association to Food
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Food> foods;

	//bi-directional many-to-one association to History
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<History> histories;

	//bi-directional many-to-one association to UserPermission
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<UserPermission> userPermissions;

	//bi-directional many-to-one association to UserRole
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<UserRole> userRoles;
}