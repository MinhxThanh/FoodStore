package edu.home.service.impl;

import edu.home.entity.CommentReplyBlog;
import edu.home.repository.CommentReplyBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentReplyBlogServiceImpl implements edu.home.service.CommentReplyBlogService {
    @Autowired
    private CommentReplyBlogRepository dao;

    @Override
    public CommentReplyBlog create(CommentReplyBlog item) {
        return dao.save(item);
    }

    @Override
    public List<CommentReplyBlog> findAllByBlogId(Long blogId) {
        return dao.findAllByBlogId(blogId);
    }

    @Override
    public void delete(Long id) {
        dao.deleteById(id);
    }
}
