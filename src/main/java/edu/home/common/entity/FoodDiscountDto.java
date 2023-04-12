package edu.home.common.entity;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class FoodDiscountDto {
	@Id
	private BigInteger foodId;
	private String foodName;
	private BigInteger discountId;
	private String discountName;

}
