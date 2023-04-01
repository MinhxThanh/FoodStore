package edu.home.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the coupons database table.
 * 
 */
@Entity
@Table(name="coupons")
@Data
public class Coupon implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private Double percentCoupon;

	private  Double amountMoneyCoupon;

	private BigInteger userUsed;

	private String code;

	@Column(name="create_date")
	@Temporal(TemporalType.DATE)
	private Date createDate;

	private String description;

	@Column(name="end_date")
	@Temporal(TemporalType.DATE)
	private Date endDate;

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

	//bi-directional many-to-one association to User
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