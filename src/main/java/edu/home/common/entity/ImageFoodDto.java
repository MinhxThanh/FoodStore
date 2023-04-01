package edu.home.common.entity;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class ImageFoodDto {
	@Id
	private long foodId;
	private String imageName;
}
