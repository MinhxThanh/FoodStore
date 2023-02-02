package edu.home.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the blog database table.
 * 
 */
@Entity
@Table(name="blogs")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Blog implements Serializable {
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

	@Column(name="view_count")
	private long viewCount;

	//bidirectional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="create_by")
	private User user;

	//bidirectional many-to-one association to CategoryBlog
	@JsonIgnore
	@OneToMany(mappedBy="blog")
	private List<CategoryBlog> categoryBlogs;

	//bidirectional many-to-one association to CommentReplyBlog
	@JsonIgnore
	@OneToMany(mappedBy="blog")
	private List<CommentReplyBlog> commentReplyBlogs;

	//bidirectional many-to-one association to CommentsBlog
	@JsonIgnore
	@OneToMany(mappedBy="blog")
	private List<CommentsBlog> commentsBlogs;

	//bidirectional many-to-one association to ImageBlog
	@JsonIgnore
	@OneToMany(mappedBy="blog")
	private List<ImageBlog> imageBlogs;
}