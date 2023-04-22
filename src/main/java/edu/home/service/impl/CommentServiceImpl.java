package edu.home.service.impl;

import edu.home.entity.Comment;
import edu.home.repository.CommentRepository;
import edu.home.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository dao;

    @Override
    public List<Comment> getAll() {
        return dao.findAll();
    }

    @Override
    public Comment create(Comment comment) {
        return dao.save(comment);
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public List<Comment> findAllByFoodId(Long foodId) {
        return dao.findAllByFoodId(foodId);
    }

    @Override
    public List<Comment> getAllByFoodId(Long foodId) {
        return dao.getAllByFoodId(foodId);
    }
}
