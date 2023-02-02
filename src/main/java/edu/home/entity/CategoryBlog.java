package edu.home.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the category_blog database table.
 * 
 */
@Entity
@Table(name="category_blog")
@Data
public class CategoryBlog implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	//bidirectional many-to-one association to Blog
	@ManyToOne
	private Blog blog;

	//bidirectional many-to-one association to Category
	@ManyToOne
	private Category category;
}