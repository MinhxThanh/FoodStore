package edu.home.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the comments_blog database table.
 * 
 */
@Entity
@Table(name="comments_blog")
@Data
public class CommentsBlog implements Serializable {
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

	//bidirectional many-to-one association to CommentReplyBlog
	@JsonIgnore
	@OneToMany(mappedBy="commentsBlog")
	private List<CommentReplyBlog> commentReplyBlogs;

	//bi-directional many-to-one association to Blog
	@ManyToOne
	private Blog blog;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	private Customer customer;
}