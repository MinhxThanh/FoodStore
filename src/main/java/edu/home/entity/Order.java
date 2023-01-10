package edu.home.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private double fee;

	@Column(name="is_display")
	private boolean isDisplay;

	@Column(name="is_watched")
	private boolean isWatched;

	@Column(name="order_date")
	private Timestamp orderDate;

	@Column(name="paid_date")
	private Timestamp paidDate;

	@Column(name="shipped_address")
	private String shippedAddress;

	@Column(name="shipped_date")
	private Timestamp shippedDate;

	@Column(name="shipped_phone")
	private String shippedPhone;

	private long status;

	//bi-directional many-to-one association to OrderDetail
	@JsonIgnore
	@OneToMany(mappedBy="order")
	private List<OrderDetail> orderDetails;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	private Customer customer;

	//bi-directional many-to-one association to Paymentmethod
	@ManyToOne
	@JoinColumn(name="payment_id")
	private Paymentmethod paymentmethod;
}