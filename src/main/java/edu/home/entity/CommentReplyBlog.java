package edu.home.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The persistent class for the comment_reply_blog database table.
 * 
 */
@Entity
@Table(name="comment_reply_blog")
public class CommentReplyBlog implements Serializable {
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

	//bi-directional many-to-one association to Blog
	@ManyToOne
	private Blog blog;

	//bi-directional many-to-one association to CommentsBlog
	@ManyToOne
	@JoinColumn(name="comments_blog_id")
	private CommentsBlog commentsBlog;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	private Customer customer;
}