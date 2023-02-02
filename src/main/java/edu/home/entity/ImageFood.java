package edu.home.entity;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the image_food database table.
 * 
 */
@Entity
@Table(name="image_food")
@Data
public class ImageFood implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name="image_name")
	private String imageName;

	//bidirectional many-to-one association to Food
	@ManyToOne
	private Food food;
}