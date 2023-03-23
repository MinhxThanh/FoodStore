package edu.home.service.impl;

import edu.home.entity.ImageFood;
import edu.home.repository.ImageFoodRepository;
import edu.home.service.ImageFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageFoodServiceImpl implements ImageFoodService {
    @Autowired
    private ImageFoodRepository dao;

    @Override
    public List<ImageFood> searchByFoodId(Long foodId) {
        return dao.searchByFoodId(foodId);
    }
}
