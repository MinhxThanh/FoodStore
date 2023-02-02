package edu.home.entity;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the customer_coupons database table.
 * 
 */
@Entity
@Table(name="customer_coupons")
@Data
public class CustomerCoupon implements Serializable {
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

	//bidirectional many-to-one association to Customer
	@ManyToOne
	private Customer customer;
}