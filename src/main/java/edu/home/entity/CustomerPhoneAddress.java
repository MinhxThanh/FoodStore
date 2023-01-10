package edu.home.entity;

import javax.persistence.*;

import java.io.Serializable;


/**
 * The persistent class for the customer_phone_address database table.
 * 
 */
@Entity
@Table(name="customer_phone_address")
@NamedQuery(name="CustomerPhoneAddress.findAll", query="SELECT c FROM CustomerPhoneAddress c")
public class CustomerPhoneAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="is_address")
	private boolean isAddress;

	@Column(name="is_default")
	private boolean isDefault;

	@Column(name="phone_or_address")
	private String phoneOrAddress;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	private Customer customer;
}