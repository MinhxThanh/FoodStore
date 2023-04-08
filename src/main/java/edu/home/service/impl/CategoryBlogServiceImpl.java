package edu.home.service.impl;

import edu.home.entity.CategoryBlog;
import edu.home.repository.CategoryBlogRepository;
import edu.home.service.CategoryBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryBlogServiceImpl implements CategoryBlogService {
    @Autowired
    private CategoryBlogRepository dao;

    @Override
    public List<CategoryBlog> findAll() {
        return dao.findAll();
    }

    @Override
    public void deleteByBlogIdAndCategoryId(Long blogId, Long categoryId) {
        dao.deleteByBlogIdAndCategoryId(blogId, categoryId);
    }

    @Override
    public CategoryBlog create(CategoryBlog categoryBlog) {
        return dao.save(categoryBlog);
    }

    @Override
    public List<CategoryBlog> findAllByBlogId(Long id) {
        return dao.findAllByBlogId(id);
    }
}
