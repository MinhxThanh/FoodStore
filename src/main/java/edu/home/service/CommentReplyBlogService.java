package edu.home.service;

import edu.home.entity.CommentReplyBlog;

import java.util.List;

public interface CommentReplyBlogService {
    CommentReplyBlog create(CommentReplyBlog item);

    List<CommentReplyBlog> findAllByBlogId(Long blogId);

    void delete(Long id);
}
