package edu.home.service;

import edu.home.entity.ImageBlog;

import java.util.List;

public interface ImageBlogService {
    List<ImageBlog> findAllByBlogId(Long blogId);

    ImageBlog create(ImageBlog imageBlog);

    void deleteById(Long id);
}
