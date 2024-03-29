package edu.home.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the order database table.
 * 
 */
@Entity
@Table(name="orders")
@Data
public class Order implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String image;

	private Double price;

	@Column(name="is_display")
	private boolean isDisplay;

	@Column(name="is_paid")
	private boolean isPaid;

	@Column(name="order_date")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date orderDate;

	@Column(name="shipped_date")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date shippedDate;

	private long status;

	//bidirectional many-to-one association to OrderDetail
	@JsonIgnore
	@OneToMany(mappedBy="order")
	private List<OrderDetail> orderDetails;

	//bidirectional many-to-one association to Customer
	@ManyToOne
	private Customer customer;

	//bidirectional many-to-one association to Paymentmethod
	@ManyToOne
	private Paymentmethod paymentmethod;

	//bidirectional many-to-one association to Shipmethod
	@ManyToOne
	private Shipmethod shipmethod;

	@ManyToOne
	private CustomerPhoneAddress customerPhoneAddress;

	@ManyToOne
	private Coupon coupon;
}