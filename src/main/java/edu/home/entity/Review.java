package edu.home.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the reviews database table.
 * 
 */
@Entity
@Table(name="reviews")
@NamedQuery(name="Review.findAll", query="SELECT r FROM Review r")
public class Review implements Serializable {

	@Id
	private long id;

	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="is_display")
	private boolean isDisplay;

	@Column(name="is_favorite")
	private boolean isFavorite;

	private long rating;

	private long status;

	@Column(name="update_date")
	private Timestamp updateDate;

	private long views;

	//bi-directional many-to-one association to Comment
	@JsonIgnore
	@OneToMany(mappedBy="review")
	private List<Comment> comments;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	private Customer customer;

	//bi-directional many-to-one association to Food
	@ManyToOne
	private Food food;
}