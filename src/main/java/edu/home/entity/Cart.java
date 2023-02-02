package edu.home.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cart database table.
 * 
 */
@Entity
@Table(name="cart")
@Data
@AllArgsConstructor @NoArgsConstructor
public class Cart implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private int quantity;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	private Customer customer;

	//bi-directional many-to-one association to Food
	@ManyToOne
	private Food food;
}