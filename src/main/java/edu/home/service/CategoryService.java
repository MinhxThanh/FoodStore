package edu.home.service;

import java.math.BigInteger;
import java.util.List;

import edu.home.entity.Category;

public interface CategoryService {

	public Category getCategory(Long categoryId);

	List<Category> findAllCategory();

	List<Category> findAll();

	Category create(Category category);

	Category update(Category category);

	void delete(Long id);

	Category findById(Long id);

	List<Category> findAllByFoodId(Long foodId);

	Category getByName(String name);
	
	Category getById(BigInteger cid);

	//Giàu
	List<Category> getAllCategoriesByFoodID(Long id);

	List<Category> findTop11();
}
