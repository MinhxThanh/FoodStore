package edu.home.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="users")
@Data
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String address;

	private String avatar;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	@Column(name="create_by")
	private long createBy;

	@Column(name="create_date")
	@Temporal(TemporalType.DATE)
	private Date createDate;

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

	//bidirectional many-to-one association to Blog
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Blog> blogs;

	//bidirectional many-to-one association to Category
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Category> categories;

	//bidirectional many-to-one association to Coupon
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Coupon> coupons;

	//bidirectional many-to-one association to Discount
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Discount> discounts;

	//bidirectional many-to-one association to Food
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Food> foods;

	//bidirectional many-to-one association to Role
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Role> roles;

	//bidirectional many-to-one association to Shipmethod
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Shipmethod> shipmethods;

	//bidirectional many-to-one association to UserRole
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<UserRole> userRoles;
}