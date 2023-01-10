package edu.home.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@Table(name="customers")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Customer implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String avatar;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	private String code;

	@Column(name="create_date")
	@Temporal(TemporalType.DATE)
	private Date createDate;

	private String email;

	private String fullname;

	private boolean gender;

	@Column(name="is_display")
	private boolean isDisplay;

	private String password;

	@Column(name="remember_token")
	private String rememberToken;

	private long status;

	private String username;

	//bi-directional many-to-one association to Cart
	@JsonIgnore
	@OneToMany(mappedBy="customer")
	private List<Cart> carts;

	//bi-directional many-to-one association to CommentReplyBlog
	@JsonIgnore
	@OneToMany(mappedBy="customer")
	private List<CommentReplyBlog> commentReplyBlogs;

	//bi-directional many-to-one association to CommentsBlog
	@JsonIgnore
	@OneToMany(mappedBy="customer")
	private List<CommentsBlog> commentsBlogs;

	//bi-directional many-to-one association to CustomerCoupon
	@JsonIgnore
	@OneToMany(mappedBy="customer")
	private List<CustomerCoupon> customerCoupons;

	//bi-directional many-to-one association to CustomerPhoneAddress
	@JsonIgnore
	@OneToMany(mappedBy="customer")
	private List<CustomerPhoneAddress> customerPhoneAddresses;

	//bi-directional many-to-one association to Order
	@JsonIgnore
	@OneToMany(mappedBy="customer")
	private List<Order> orders;

	//bi-directional many-to-one association to Review
	@JsonIgnore
	@OneToMany(mappedBy="customer")
	private List<Review> reviews;
}