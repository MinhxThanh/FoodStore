package edu.home.entity;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the notification database table.
 * 
 */
@Entity
@Table(name="notifications")
@Data
public class Notification implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String content;

	@Column(name="create_date")
	@Temporal(TemporalType.DATE)
	private Date createDate;

	@Column(name="is_watched")
	private boolean isWatched;

	private String title;

	//bidirectional many-to-one association to Customer
	@ManyToOne
	private Customer customer;
}