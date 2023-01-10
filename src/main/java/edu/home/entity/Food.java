package edu.home.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the foods database table.
 * 
 */
@Entity
@Table(name="foods")
@NamedQuery(name="Food.findAll", query="SELECT f FROM Food f")
public class Food implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="create_date")
	private Timestamp createDate;

	private String description;

	@Column(name="is_display")
	private boolean isDisplay;

	private String name;

	private double price;

	@Column(name="quantity_limit")
	private long quantityLimit;

	private long status;

	@Column(name="view_count")
	private long viewCount;

	//bi-directional many-to-one association to Cart
	@JsonIgnore
	@OneToMany(mappedBy="food")
	private List<Cart> carts;

	//bi-directional many-to-one association to CategoryBlog
	@JsonIgnore
	@OneToMany(mappedBy="food")
	private List<CategoryBlog> categoryBlogs;

	//bi-directional many-to-one association to CategoryFood
	@JsonIgnore
	@OneToMany(mappedBy="food")
	private List<CategoryFood> categoryFoods;

	//bi-directional many-to-one association to Discount
	@JsonIgnore
	@OneToMany(mappedBy="food")
	private List<Discount> discounts;

	//bi-directional many-to-one association to FoodCoupon
	@JsonIgnore
	@OneToMany(mappedBy="food")
	private List<FoodCoupon> foodCoupons;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="create_by")
	private User user;

	//bi-directional many-to-one association to Image
	@JsonIgnore
	@OneToMany(mappedBy="food")
	private List<Image> images;

	//bi-directional many-to-one association to Notification
	@JsonIgnore
	@OneToMany(mappedBy="food")
	private List<Notification> notifications;

	//bi-directional many-to-one association to OrderDetail
	@JsonIgnore
	@OneToMany(mappedBy="food")
	private List<OrderDetail> orderDetails;

	//bi-directional many-to-one association to Review
	@JsonIgnore
	@OneToMany(mappedBy="food")
	private List<Review> reviews;
}