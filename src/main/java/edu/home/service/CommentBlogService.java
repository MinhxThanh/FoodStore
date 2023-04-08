package edu.home.service;

import java.util.List;

import edu.home.entity.Blog;
import edu.home.entity.CommentsBlog;


public interface CommentBlogService {

    List<CommentsBlog> findAllByBlogId(Long blogId);

    CommentsBlog create(CommentsBlog item);

    void deleteById(Long id);

    CommentsBlog findById(Long commentId);
}
