package edu.home.entity;

import lombok.*;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the category_blog database table.
 * 
 */
@Entity
@Table(name="category_blog")
@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
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