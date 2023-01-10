package edu.home.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The persistent class for the comments database table.
 * 
 */
@Entity
@Table(name="comments")
@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String content;

	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="is_display")
	private boolean isDisplay;

	private long status;

	private String title;

	@Column(name="update_date")
	private Timestamp updateDate;

	//bi-directional many-to-one association to Review
	@ManyToOne
	private Review review;
}