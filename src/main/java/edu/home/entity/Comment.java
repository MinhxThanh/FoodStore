package edu.home.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;



/**
 * The persistent class for the comment database table.
 * 
 */
@Entity
@Table(name="comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String content;

	@Column(name="create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createDate;

	@Column(name="is_display")
	private boolean isDisplay;

	private String title;

	@Column(name="update_date")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date updateDate;

	//bidirectional many-to-one association to Review
	@ManyToOne
	private Review review;
}