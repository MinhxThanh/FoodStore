package edu.home.common.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcedureFoods {
	@Id
	private BigInteger id; 
	private String name;
	private double price;
	private BigInteger quantityLimit;
	private BigInteger quantitySell;
	private BigInteger viewCount;
	private String description;
	private Date createDate;
	private BigInteger createBy;
	private boolean isDisplay;
	private String imageName;
	private String displayName;
	private String category;
}
