package edu.home.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the coupon database table.
 * 
 */
@Entity
@Table(name="coupons")
public class Coupon implements Serializable {

	@Id
	private long id;

	private double amount;

	private String code;

	@Column(name="create_date")
	@Temporal(TemporalType.DATE)
	private Date createDate;

	private String description;

	@Column(name="end_date")
	@Temporal(TemporalType.DATE)
	private Date endDate;

	@Column(name="food_limit")
	private long foodLimit;

	@Column(name="is_display")
	private boolean isDisplay;

	@Column(name="is_fixed")
	private boolean isFixed;

	private String name;

	@Column(name="start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate;

	private long status;

	@Column(name="user_limit")
	private long userLimit;

	//bidirectional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="create_by")
	private User user;

	//bidirectional many-to-one association to CustomerCoupon
	@JsonIgnore
	@OneToMany(mappedBy="coupon")
	private List<CustomerCoupon> customerCoupons;

	//bidirectional many-to-one association to FoodCoupon
	@JsonIgnore
	@OneToMany(mappedBy="coupon")
	private List<FoodCoupon> foodCoupons;
}