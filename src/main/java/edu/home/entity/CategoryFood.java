package edu.home.entity;

import javax.persistence.*;

import java.io.Serializable;


/**
 * The persistent class for the category_foods database table.
 * 
 */
@Entity
@Table(name="category_foods")
public class CategoryFood implements Serializable {
	@Id
	private Long id;

	//bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	//bi-directional many-to-one association to Food
	@ManyToOne
	private Food food;
}