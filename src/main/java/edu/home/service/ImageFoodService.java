package edu.home.service;

import edu.home.entity.ImageFood;

import java.util.List;

public interface ImageFoodService {
    List<ImageFood> searchByFoodId(Long foodId);
    
    ImageFood save(ImageFood imageFood);
	ImageFood findById(Long id);
	ImageFood update(ImageFood imageFood);
	List<ImageFood> findByFoodId(Long id);
	List<ImageFood> findAll();
	void delete(Long id);

	void deleteByImageName(String imageName);
}
