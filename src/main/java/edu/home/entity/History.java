package edu.home.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The persistent class for the histories database table.
 * 
 */
@Entity
@Table(name="histories")
@NamedQuery(name="History.findAll", query="SELECT h FROM History h")
public class History implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="create_date")
	private Timestamp createDate;

	private String memo;

	@Column(name="record_id")
	private long recordId;

	@Column(name="table_name")
	private String tableName;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;
}