package edu.home.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the category_foods database table.
 * 
 */
@Entity
@Table(name="category_foods")
@Data
@AllArgsConstructor @NoArgsConstructor
public class CategoryFood implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	//bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	//bi-directional many-to-one association to Food
	@ManyToOne
	private Food food;
}