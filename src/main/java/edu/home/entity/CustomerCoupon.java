package edu.home.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The persistent class for the customer_coupons database table.
 * 
 */
@Entity
@Table(name="customer_coupons")
@NamedQuery(name="CustomerCoupon.findAll", query="SELECT c FROM CustomerCoupon c")
public class CustomerCoupon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="create_date")
	private Timestamp createDate;

	private long status;

	//bi-directional many-to-one association to Coupon
	@ManyToOne
	private Coupon coupon;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	private Customer customer;
}