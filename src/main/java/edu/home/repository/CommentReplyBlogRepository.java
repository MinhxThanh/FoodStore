package edu.home.repository;

import edu.home.entity.CommentReplyBlog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentReplyBlogRepository extends JpaRepository<CommentReplyBlog, Long> {
}