package edu.home.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the customer's database table.
 * 
 */
@Entity
@Table(name="customers")
@Data
public class Customer implements Serializable {
	@Id
	private String email;

	private String avatar;

	@Column(name = "birthday")
	@Temporal(TemporalType.DATE)
	private Date birthday;

	@Column(name="create_date")
	@Temporal(TemporalType.DATE)
	private Date createDate;

	@Column(name="first_name")
	private String firstName;

	private String fullname;

	private boolean gender;

	@Column(name="is_display")
	private boolean isDisplay;

	@Column(name="last_name")
	private String lastName;

	private String password;

	@Column(name="remember_token")
	private String rememberToken;

	private long status;

	//bidirectional many-to-one association to Cart
	@JsonIgnore
	@OneToMany(mappedBy="customer")
	private List<Cart> carts;

	//bidirectional many-to-one association to CommentReplyBlog
	@JsonIgnore
	@OneToMany(mappedBy="customer")
	private List<CommentReplyBlog> commentReplyBlogs;

	//bidirectional many-to-one association to CommentsBlog
	@JsonIgnore
	@OneToMany(mappedBy="customer")
	private List<CommentsBlog> commentsBlogs;

	//bidirectional many-to-one association to CustomerCoupon
	@JsonIgnore
	@OneToMany(mappedBy="customer")
	private List<CustomerCoupon> customerCoupons;

	//bidirectional many-to-one association to CustomerPhoneAddress
	@JsonIgnore
	@OneToMany(mappedBy="customer")
	private List<CustomerPhoneAddress> customerPhoneAddresses;

	//bidirectional many-to-one association to Notification
	@JsonIgnore
	@OneToMany(mappedBy="customer")
	private List<Notification> notifications;

	//bidirectional many-to-one association to Order
	@JsonIgnore
	@OneToMany(mappedBy="customer")
	private List<Order> orders;

	//bidirectional many-to-one association to Review
	@JsonIgnore
	@OneToMany(mappedBy="customer")
	private List<Review> reviews;
}