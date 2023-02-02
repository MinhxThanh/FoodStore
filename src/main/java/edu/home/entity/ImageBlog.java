package edu.home.entity;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the image_blog database table.
 * 
 */
@Entity
@Table(name="image_blog")
@Data
public class ImageBlog implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name="image_name")
	private String imageName;

	//bidirectional many-to-one association to Blog
	@ManyToOne
	private Blog blog;
}