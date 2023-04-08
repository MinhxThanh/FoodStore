package edu.home.service;

import edu.home.entity.CategoryBlog;

import java.util.List;

public interface CategoryBlogService {
    List<CategoryBlog> findAll();

    void deleteByBlogIdAndCategoryId(Long blogId, Long categoryId);

    CategoryBlog create(CategoryBlog categoryBlog);

    List<CategoryBlog> findAllByBlogId(Long id);
}
