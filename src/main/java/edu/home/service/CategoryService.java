package edu.home.service;

import edu.home.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category update(Category category);

    void delete(Long id);

    Category findById(Long id);

    List<Category> findAllByFoodId(Long foodId);
    
//    Giàu
    List<Category> getAllCategoriesByFoodID(Long id);
    
//    Giàu
    Category create(Category category);
    
}
