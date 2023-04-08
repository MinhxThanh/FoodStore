package edu.home.repository;

import edu.home.entity.ImageBlog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageBlogRepository extends JpaRepository<ImageBlog, Long> {
    List<ImageBlog> findAllByBlogId(Long blogId);
}