package edu.home.entity;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the food_coupons database table.
 * 
 */
@Entity
@Table(name="food_coupons")
@Data
public class FoodCoupon implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name="create_date")
	@Temporal(TemporalType.DATE)
	private Date createDate;

	private long status;

	//bidirectional many-to-one association to Coupon
	@ManyToOne
	private Coupon coupon;

	//bidirectional many-to-one association to Food
	@ManyToOne
	private Food food;
}