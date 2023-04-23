package edu.home.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import edu.home.entity.Blog;
import edu.home.entity.Category;
import edu.home.entity.CategoryBlog;

import edu.home.entity.CommentsBlog;
import edu.home.entity.ImageBlog;
import edu.home.entity.Role;
import edu.home.entity.User;
import edu.home.repository.BlogRepository;
import edu.home.repository.CategoryBlogRepository;
import edu.home.repository.CategoryRepository;

import edu.home.repository.RoleRepository;
import edu.home.repository.UserRepository;
import edu.home.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {
	@Autowired
	BlogRepository blogRepo;
	@Autowired
	UserRepository userR;
	@Autowired
	CategoryBlogRepository cateBlogs;
	
	
	
	
	public List<Blog> findAllBlog() {
		// TODO Auto-generated method stub
		return blogRepo.findAll();
	}

	@Override
	public void deleteBlogs(Long id) {
		// TODO Auto-generated method stub
		blogRepo.deleteById(id);
	}

	@Override
	public Blog findBlogsByUser(Long userId) {
		User user = null;
		return blogRepo.findById(user.getId()).get();
		
		
	}

	@Override
	public Page<Blog> searchBlogs(String keyword, Pageable pageable) {
//		Pageable pageable = PageRequest.of(0, 2, Sort.by("id"));
		Page<Blog> list = blogRepo.searchBlogs(keyword, pageable);
			return list;
	}

	@Override
	public List<Blog> findBLogsByCategory(Long categoryId) {
		// TODO Auto-generated method stub
		List<Blog> list = blogRepo.findBLogsByCategory(categoryId);
		return list ;
	}


	@Override
	public Blog findBlogsById(Long id) {
		// TODO Auto-generated method stub
		return blogRepo.findById(id).get();
	}


	@Override
	public Blog createBlogs(Blog blog) {
//		CategoryBlog blog1 = cateBlogs.findById(cateroryId).get();
//		Blog crBlog= null ;
//		crBlog.setCategoryBlogs((List<CategoryBlog>) blog1);
		blog  = blogRepo.save(blog);
		return blog;
	}

	@Override
	public Blog updateBlogs(Blog blog) {
		// TODO Auto-generated method stub
		return blogRepo.save(blog);
	}

	@Override
	 public void updateViewCount(Long id, Long viewCount ) {
		// TODO Auto-generated method stub
		 blogRepo.updateViewCount(viewCount, id);
	}

	@Override
	public List<Blog> topBaBlog() {
		// TODO Auto-generated method stub
		List<Blog> list3 = blogRepo.topBaBlog();
		return list3;
	}

	@Override
	public List<Blog> findAllByUserEmail(String email) {
		return blogRepo.findAllByUserEmail(email);
	}

	@Override
	public Page<Blog> findByCategoryId(Optional<Long> category_id, Pageable pageable) {
		return blogRepo.findByCategoryId(category_id, pageable);
	}
}
