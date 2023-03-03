package edu.home.service;

import edu.home.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category create(Category category);

    Category update(Category category);

    void delete(Long id);

    Category findById(Long id);
}
