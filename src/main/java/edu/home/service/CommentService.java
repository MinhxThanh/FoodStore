package edu.home.service;

import edu.home.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAll();

    Comment create(Comment comment);

    void deleteById(Long id);

    List<Comment> findAllByFoodId(Long foodId);
}
