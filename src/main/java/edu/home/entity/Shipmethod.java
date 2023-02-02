package edu.home.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the shipmethods database table.
 * 
 */
@Entity
@Table(name="shipmethods")
@Data
public class Shipmethod implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String description;

	@Column(name="is_display")
	private boolean isDisplay;

	private String name;

	private double price;

	//bidirectional many-to-one association to Order
	@JsonIgnore
	@OneToMany(mappedBy="shipmethod")
	private List<Order> orders;

	//bidirectional many-to-one association to User
	@ManyToOne
	private User user;
}