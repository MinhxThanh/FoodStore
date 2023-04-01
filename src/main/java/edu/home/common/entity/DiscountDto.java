package edu.home.common.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor 
@NoArgsConstructor
public class DiscountDto {
	@Id
	private Long id;
	private Long food;
	private String name;
	private boolean fixed;
	private Date startDate;
	private Date createDate;
	private Date endDate;
	private Long percentDiscount;
	private String createBy;
	private Long createByID;
	private boolean display;
}
