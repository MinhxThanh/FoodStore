package edu.home.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the blog database table.
 * 
 */
@Entity
@Table(name="blog")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog implements Serializable {
	@Id
	private Long id;

	private String content;

	@Column(name="create_date")
	private Date createDate;

	@Column(name="image_name")
	private String imageName;

	private long status;

	private String title;

	@Column(name="view_count")
	private long viewCount;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="create_by")
	private User user;

	//bi-directional many-to-one association to CommentReplyBlog
	@JsonIgnore
	@OneToMany(mappedBy="blog")
	private List<CommentReplyBlog> commentReplyBlogs;

	//bi-directional many-to-one association to CommentsBlog
	@JsonIgnore
	@OneToMany(mappedBy="blog")
	private List<CommentsBlog> commentsBlogs;
}