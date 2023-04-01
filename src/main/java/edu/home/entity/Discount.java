package edu.home.entity;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the discount database table.
 * 
 */
@Entity
@Table(name="discounts")
@Data
public class Discount implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name="percent_discount")
	private Long percentDiscount;

	@Column(name="create_date")
	@Temporal(TemporalType.DATE)
	private Date createDate;

	@Column(name="end_date")
	@Temporal(TemporalType.DATE)
	private Date endDate;

	@Column(name="is_display")
	private boolean isDisplay;

	@Column(name="is_fixed")
	private boolean isFixed;

	private String name;

	@Column(name="start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate;

	//bidirectional many-to-one association to Food
	@ManyToOne
	private Food food;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="create_by")
	private User user;
}