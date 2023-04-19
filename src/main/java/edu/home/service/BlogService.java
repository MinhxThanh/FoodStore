package edu.home.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import edu.home.entity.Blog;

import edu.home.entity.CommentsBlog;
import edu.home.entity.User;

public interface BlogService {
	public List<Blog> findAllBlog(); 

	public Blog findBlogsById(Long id);

	public List<Blog> findBLogsByCategory(Long categoryId);
	void updateViewCount(Long id,Long viewCount);

	public Blog createBlogs(Blog blog);

	public Blog updateBlogs(Blog blog);

	public void deleteBlogs(Long id);
	
	Blog findBlogsByUser(Long userId);

	//Search post
	Page<Blog> searchBlogs(String keyword);
//	public List<CommentsBlog> save(User user);
//	public List<Feature> saveCommentReply(User user);

	List<Blog> topBaBlog();


	List<Blog> findAllByUserEmail(String email);
}
