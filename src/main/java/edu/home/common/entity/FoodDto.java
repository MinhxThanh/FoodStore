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
	private Date createDate;
	private String description;
	private boolean isDisplay;
	private String name;
	private double price;
	private Long quantityLimit;
	private Long quantitySell;
	private String userName;
	private String createBy;
	private String imageFood;
	private Long categoryFoodId;
}
