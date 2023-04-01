package edu.home.repository;

import edu.home.entity.CategoryBlog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryBlogRepository extends JpaRepository<CategoryBlog, Long> {
}