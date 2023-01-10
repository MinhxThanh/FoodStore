package edu.home.repository;

import edu.home.entity.CommentsBlog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsBlogRepository extends JpaRepository<CommentsBlog, Long> {
}