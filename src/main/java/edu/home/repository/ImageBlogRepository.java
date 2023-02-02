package edu.home.repository;

import edu.home.entity.ImageBlog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageBlogRepository extends JpaRepository<ImageBlog, Long> {
}