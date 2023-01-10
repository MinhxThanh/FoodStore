package edu.home.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the categories database table.
 * 
 */
@Entity
@Table(name="categories")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {

	@Id
	private Long id;

	private String color;

	@Column(name="create_date")
	private Timestamp createDate;

	private String description;

	@Column(name="display_name")
	private String displayName;

	@Column(name="image_name")
	private String imageName;

	@Column(name="is_display")
	private boolean isDisplay;

	private String name;

	private long type;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="create_by")
	private User user;

	//bi-directional many-to-one association to CategoryBlog
	@JsonIgnore
	@OneToMany(mappedBy="category")
	private List<CategoryBlog> categoryBlogs;

	//bi-directional many-to-one association to CategoryFood
	@JsonIgnore
	@OneToMany(mappedBy="category")
	private List<CategoryFood> categoryFoods;
}