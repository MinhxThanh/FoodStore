package edu.home.repository;

import edu.home.entity.CommentReplyBlog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentReplyBlogRepository extends JpaRepository<CommentReplyBlog, Long> {
    List<CommentReplyBlog> findAllByBlogId(Long blogId);
}