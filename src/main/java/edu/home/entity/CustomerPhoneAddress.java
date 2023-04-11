package edu.home.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


/**
 * The persistent class for the customer_phone_address database table.
 * 
 */
@Entity
@Table(name="customer_phone_address")
@Data
public class CustomerPhoneAddress implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String address;

	private String username;

	@Column(name="city_province")
	private String cityProvince;

	@Column(name="is_default")
	private boolean isDefault;

	private String phone;

	//bidirectional many-to-one association to Customer
	@ManyToOne
	private Customer customer;

	@JsonIgnore
	@OneToMany(mappedBy = "customerPhoneAddress")
	private List<Order> orders;
}