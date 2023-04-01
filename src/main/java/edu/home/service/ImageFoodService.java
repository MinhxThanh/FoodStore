package edu.home.service;

import edu.home.entity.ImageFood;

import java.util.List;

public interface ImageFoodService {
    List<ImageFood> searchByFoodId(Long foodId);
	ImageFood findById(Long id);
	ImageFood update(ImageFood imageFood);
	List<ImageFood> findAll();
	
//	Giàu
	ImageFood save(ImageFood imageFood);
//	Giàu
	void delete(Long id);
}
