package edu.home.service;

import java.util.List;

import edu.home.entity.Category;

public interface CategoryService {

	//get
public Category getCategory(Long categoryId);

	//getAll
List<Category> findAllCategory();

List<Category> findAll();

Category create(Category category);

Category update(Category category);

void delete(Long id);

Category findById(Long id);

List<Category> findAllByFoodId(Long foodId);

Category getByName(String name);

//Giàu
List<Category> getAllCategoriesByFoodID(Long id);
}
