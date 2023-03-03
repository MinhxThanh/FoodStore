package edu.home.service;

import edu.home.entity.ImageFood;

import java.util.List;

public interface ImageFoodService {
    List<ImageFood> searchByFoodId(Long foodId);
}
