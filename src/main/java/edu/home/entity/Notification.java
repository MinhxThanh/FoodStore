package edu.home.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The persistent class for the notifications database table.
 * 
 */
@Entity
@Table(name="notifications")
@NamedQuery(name="Notification.findAll", query="SELECT n FROM Notification n")
public class Notification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String content;

	@Column(name="create_at")
	private Timestamp createAt;

	@Column(name="is_watched")
	private long isWatched;

	@Column(name="record_id")
	private long recordId;

	@Column(name="table_name")
	private String tableName;

	//bi-directional many-to-one association to Food
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Food food;


}