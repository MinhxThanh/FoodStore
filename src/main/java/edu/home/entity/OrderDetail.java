package edu.home.entity;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the order_details database table.
 * 
 */
@Entity
@Table(name="order_details")
@Data
public class OrderDetail implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name="coupon_code")
	private String couponCode;

	@Column(name="discount_amount")
	private double discountAmount;

	@Column(name="is_display")
	private boolean isDisplay;

	private double price;

	private long quantity;

	private long status;

	//bidirectional many-to-one association to Food
	@ManyToOne
	private Food food;

	//bidirectional many-to-one association to Order
	@ManyToOne
	private Order order;
}