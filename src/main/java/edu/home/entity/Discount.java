package edu.home.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The persistent class for the discounts database table.
 * 
 */
@Entity
@Table(name="discounts")
@NamedQuery(name="Discount.findAll", query="SELECT d FROM Discount d")
public class Discount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private double amount;

	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="end_date")
	private Timestamp endDate;

	@Column(name="is_display")
	private boolean isDisplay;

	@Column(name="is_fixed")
	private boolean isFixed;

	private String name;

	@Column(name="start_date")
	private Timestamp startDate;

	//bi-directional many-to-one association to Food
	@ManyToOne
	private Food food;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="create_by")
	private User user;
}