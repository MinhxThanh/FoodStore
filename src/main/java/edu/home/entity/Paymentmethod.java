package edu.home.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the paymentmethods database table.
 * 
 */
@Entity
@Table(name="paymentmethods")
@NamedQuery(name="Paymentmethod.findAll", query="SELECT p FROM Paymentmethod p")
public class Paymentmethod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String code;

	private String description;

	@Column(name="image_name")
	private String imageName;

	@Column(name="is_display")
	private boolean isDisplay;

	private String name;

	private long status;

	//bi-directional many-to-one association to Order
	@JsonIgnore
	@OneToMany(mappedBy="paymentmethod")
	private List<Order> orders;
}