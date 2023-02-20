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
@AllArgsConstructor
@NoArgsConstructor
public class ImageNameFood {
	@Id
	private BigInteger idFood;
	private String name;
	private String discription;
	private double price;
	private String imageName;
}
