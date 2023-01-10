package edu.home.entity;

import javax.persistence.*;

import java.io.Serializable;


/**
 * The persistent class for the order_details database table.
 * 
 */
@Entity
@Table(name="order_details")
@NamedQuery(name="OrderDetail.findAll", query="SELECT o FROM OrderDetail o")
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="coupon_code")
	private String couponCode;

	@Column(name="discount_amount")
	private double discountAmount;

	@Column(name="is_display")
	private boolean isDisplay;

	@Column(name="is_fixed")
	private boolean isFixed;

	private String memo;

	private double price;

	private long quantity;

	private long status;

	//bi-directional many-to-one association to Food
	@ManyToOne
	private Food food;

	//bi-directional many-to-one association to Order
	@ManyToOne
	private Order order;
}