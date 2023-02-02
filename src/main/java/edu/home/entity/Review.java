package edu.home.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the review database table.
 * 
 */
@Entity
@Table(name="reviews")
@Data
public class Review implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name="create_date")
	@Temporal(TemporalType.DATE)
	private Date createDate;

	@Column(name="is_display")
	private boolean isDisplay;

	@Column(name="is_favorite")
	private boolean isFavorite;

	private long rating;

	@Column(name="update_date")
	@Temporal(TemporalType.DATE)
	private Date updateDate;

	//bidirectional many-to-one association to Comment
	@JsonIgnore
	@OneToMany(mappedBy="review")
	private List<Comment> comments;

	//bidirectional many-to-one association to Customer
	@ManyToOne
	private Customer customer;

	//bidirectional many-to-one association to Food
	@ManyToOne
	private Food food;
}