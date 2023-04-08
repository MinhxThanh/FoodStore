package edu.home.service.impl;

import edu.home.entity.ImageBlog;
import edu.home.repository.ImageBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageBlogService implements edu.home.service.ImageBlogService {
    @Autowired
    private ImageBlogRepository dao;

    @Override
    public List<ImageBlog> findAllByBlogId(Long blogId) {
        return dao.findAllByBlogId(blogId);
    }

    @Override
    public ImageBlog create(ImageBlog imageBlog) {
        return dao.save(imageBlog);
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }
}
