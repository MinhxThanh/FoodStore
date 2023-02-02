package edu.home.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the foods database table.
 * 
 */
@Entity
@Table(name="foods")
@Data
public class Food implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name="create_date")
	@Temporal(TemporalType.DATE)
	private Date createDate;

	private String description;

	@Column(name="is_display")
	private boolean isDisplay;

	private String name;

	private double price;

	@Column(name="quantity_limit")
	private long quantityLimit;

	@Column(name="quantity_sell")
	private long quantitySell;

	@Column(name="view_count")
	private long viewCount;

	//bidirectional many-to-one association to Cart
	@JsonIgnore
	@OneToMany(mappedBy="food")
	private List<Cart> carts;

	//bidirectional many-to-one association to CategoryFood
	@JsonIgnore
	@OneToMany(mappedBy="food")
	private List<CategoryFood> categoryFoods;

	//bidirectional many-to-one association to Discount
	@JsonIgnore
	@OneToMany(mappedBy="food")
	private List<Discount> discounts;

	//bidirectional many-to-one association to FoodCoupon
	@JsonIgnore
	@OneToMany(mappedBy="food")
	private List<FoodCoupon> foodCoupons;

	//bidirectional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="create_by")
	private User user;

	//bidirectional many-to-one association to ImageFood
	@JsonIgnore
	@OneToMany(mappedBy="food")
	private List<ImageFood> imageFoods;

	//bidirectional many-to-one association to OrderDetail
	@JsonIgnore
	@OneToMany(mappedBy="food")
	private List<OrderDetail> orderDetails;

	//bidirectional many-to-one association to Review
	@JsonIgnore
	@OneToMany(mappedBy="food")
	private List<Review> reviews;
}