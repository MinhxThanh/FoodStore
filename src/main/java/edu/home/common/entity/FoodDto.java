package edu.home.common.entity;

import java.util.Date;

import javax.persistence.Id;

import edu.home.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodDto {
	@Id
	private Long id; 
	private String name;
	private double price;
	private Long quantityLimit;
	private Long quantitySell;
	private String description;
	private Date createDate;
	private boolean isDisplay;
	
	private String imageFood;
	private Long categoryFoodId;
	private String createBy;
	
	
}
