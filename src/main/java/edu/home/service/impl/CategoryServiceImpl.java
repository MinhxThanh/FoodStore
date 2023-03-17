package edu.home.service.impl;

import edu.home.entity.Category;
import edu.home.repository.CategoryRepository;
import edu.home.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import edu.home.entity.Category;
import edu.home.repository.CategoryFoodRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository dao;
	@Autowired
	CategoryFoodRepository categoryFoodRepository;

    @Override
    public List<Category> findAll() {
        return dao.findAll();
    }

    @Override
    public Category create(Category category) {
        return dao.save(category);
    }

    @Override
    public Category update(Category category) {
        return dao.save(category);
    }

    @Override
    public void delete(Long id) {
        dao.deleteById(id);
    }

    @Override
    public Category findById(Long id) {
        return dao.findById(id).get();
    }
    
    public List<Category> getAllCategory() {
		return dao.findAll();
	}
    @Override
	    public List<Category> getAllCategoriesByFoodID(Long id) {
	        return dao.findAllCategoriesByFoodId(id);
	    }
}
