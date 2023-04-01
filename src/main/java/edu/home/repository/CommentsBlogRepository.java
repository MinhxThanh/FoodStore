package edu.home.repository;

import edu.home.entity.CommentsBlog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsBlogRepository extends JpaRepository<CommentsBlog, Long> {

    List<CommentsBlog> findAllByBlogId(Long id);
}