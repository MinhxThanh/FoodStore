package edu.home.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The persistent class for the food_coupons database table.
 * 
 */
@Entity
@Table(name="food_coupons")
@NamedQuery(name="FoodCoupon.findAll", query="SELECT f FROM FoodCoupon f")
public class FoodCoupon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="create_date")
	private Timestamp createDate;

	private long status;

	//bi-directional many-to-one association to Coupon
	@ManyToOne
	private Coupon coupon;

	//bi-directional many-to-one association to Food
	@ManyToOne
	private Food food;
}