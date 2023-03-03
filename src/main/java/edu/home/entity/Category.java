package edu.home.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@Table(name="categories")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Category implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name="create_date")
	@Temporal(TemporalType.DATE)
	private Date createDate;

	private String description;

	@Column(name="image_name")
	private String imageName;

	@Column(name="is_display")
	private boolean isDisplay;

	private String name;

	//bidirectional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="create_by")
	private User user;

	//bidirectional many-to-one association to CategoryBlog
	@JsonIgnore
	@OneToMany(mappedBy="category")
	private List<CategoryBlog> categoryBlogs;

	//bidirectional many-to-one association to CategoryFood
	@JsonIgnore
	@OneToMany(mappedBy="category")
	private List<CategoryFood> categoryFoods;
}