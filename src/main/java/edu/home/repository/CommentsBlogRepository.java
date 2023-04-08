package edu.home.repository;

import edu.home.entity.CommentsBlog;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CommentsBlogRepository extends JpaRepository<CommentsBlog, Long> {
    @Query(value = "select* from comments_blog cb join comment_reply_blog rl on cb.id = rl.comments_blog_id where cb.blog_id =?1", nativeQuery = true)
    List<CommentsBlog> findAllById(Long BlogId);

    List<CommentsBlog> findAllByBlogId(Long blogId);
//	
//	List<CommentsBlog> findByFeatureId(Long featureId);
}