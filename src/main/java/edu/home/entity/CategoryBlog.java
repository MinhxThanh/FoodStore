package edu.home.entity;

import javax.persistence.*;

import java.io.Serializable;


/**
 * The persistent class for the category_blog database table.
 * 
 */
@Entity
@Table(name="category_blog")
@NamedQuery(name="CategoryBlog.findAll", query="SELECT c FROM CategoryBlog c")
public class CategoryBlog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	//bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	//bi-directional many-to-one association to Food
	@ManyToOne
	@JoinColumn(name="blog_id")
	private Food food;
}