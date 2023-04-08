package edu.home.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.home.entity.Blog;
import edu.home.entity.CommentsBlog;
import edu.home.repository.BlogRepository;
import edu.home.repository.CommentsBlogRepository;
import edu.home.service.CommentBlogService;

@Service
public class CommentBlogServiceImpl implements CommentBlogService {
	@Autowired
    private CommentsBlogRepository dao;

    @Override
    public List<CommentsBlog> findAllByBlogId(Long blogId) {
        return dao.findAllByBlogId(blogId);
    }

    @Override
    public CommentsBlog create(CommentsBlog item) {
        return dao.save(item);
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public CommentsBlog findById(Long commentId) {
        return dao.findById(commentId).get();
    }
}
