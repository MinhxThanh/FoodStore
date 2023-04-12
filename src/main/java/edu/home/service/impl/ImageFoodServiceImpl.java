package edu.home.service.impl;

import edu.home.entity.ImageFood;
import edu.home.repository.ImageFoodRepository;
import edu.home.service.ImageFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.home.entity.ImageFood;

@Service
public class ImageFoodServiceImpl implements ImageFoodService {
    @Autowired
    private ImageFoodRepository dao;

    @Override
    public List<ImageFood> searchByFoodId(Long foodId) {
        return dao.searchByFoodId(foodId);
    }
    
    @Override
	public ImageFood save(ImageFood imageFood) {
		
		return dao.save(imageFood);
	}

	@Override
	public ImageFood findById(Long id) {
		return dao.findById(id).get();
	}

	@Override
	public ImageFood update(ImageFood imageFood) {
		return dao.save(imageFood);
	}

	@Override
	public List<ImageFood> findByFoodId(Long id) {
		return dao.findByFoodId(id);
	}

	@Override
	public List<ImageFood> findAll() {
		return dao.findAll();
	}

	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}

	@Override
	public void deleteByImageName(String imageName) {
		dao.deleteByImageName(imageName);
	}
}
