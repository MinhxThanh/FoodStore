package edu.home.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the comments_blog database table.
 * 
 */
@Entity
@Table(name="comments_blog")
public class CommentsBlog implements Serializable {
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

	//bi-directional many-to-one association to CommentReplyBlog
	@JsonIgnore
	@OneToMany(mappedBy="commentsBlog")
	private List<CommentReplyBlog> commentReplyBlogs;

	//bi-directional many-to-one association to Blog
	@ManyToOne
	@JoinColumn(name="blog_id")
	private Blog blog;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	private Customer customer;
}