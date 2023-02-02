package edu.home.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * The persistent class for the comment_reply_blog database table.
 * 
 */
@Entity
@Table(name="comment_reply_blog")
@Data
public class CommentReplyBlog implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String content;

	@Column(name="create_date")
	@Temporal(TemporalType.DATE)
	private Date createDate;

	@Column(name="is_display")
	private boolean isDisplay;

	private long status;

	private String title;

	@Column(name="update_date")
	@Temporal(TemporalType.DATE)
	private Date updateDate;

	//bidirectional many-to-one association to Blog
	@ManyToOne
	private Blog blog;

	//bidirectional many-to-one association to CommentsBlog
	@ManyToOne
	@JoinColumn(name="comments_blog_id")
	private CommentsBlog commentsBlog;

	//bidirectional many-to-one association to Customer
	@ManyToOne
	private Customer customer;
}