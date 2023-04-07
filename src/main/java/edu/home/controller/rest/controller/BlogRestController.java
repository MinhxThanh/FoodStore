package edu.home.controller.rest.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.home.common.entity.BlogInfo;
import edu.home.entity.Blog;
import edu.home.service.BlogService;
import edu.home.service.UserAccountService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/blog")
public class BlogRestController {
	@Autowired
	BlogService blogService;
	@Autowired
	private UserAccountService userAccountService;

	@GetMapping(value = "getAll")
	public List<Blog> getAllblog() {
//		System.out.println("\n dang chay getall blog \n");
		return blogService.findAllBlog();
	}

	@PostMapping(value = "create")
	public ResponseEntity<?> createblog(@RequestBody BlogInfo blogInfo) {
		try {

			Blog blog = new Blog();

//			blog.setId(blogInfo.getId());
			blog.setContent(blogInfo.getContent());
			blog.setCreateDate(blogInfo.getCreateDate());
			blog.setIsDisplay(blogInfo.getIsDisplay());
			blog.setStatus(blogInfo.getStatus());
			blog.setTitle(blogInfo.getTitle());
			blog.setViewCount(blogInfo.getViewCount());
			blog.setUser(userAccountService.findByUsernameOrEmail(blogInfo.getCreateBy()));

			return ResponseEntity.ok(blogService.createBlogs(blog));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.noContent().build();
		}
	}

	@DeleteMapping(value = "delete/{id}")
	public void deleteblog(@PathVariable("id") Long id) {
		System.out.println("\n dang chay delete \n");
		blogService.deleteBlogs(id);
	}

	@PutMapping(value = "update")
	public ResponseEntity<?> updateCategory(@RequestBody BlogInfo blogInfo) {
		try {
			Blog oldBlog = blogService.findBlogsById(blogInfo.getId());
			Blog blog = new Blog();

			blog.setId(blogInfo.getId());
			blog.setContent(blogInfo.getContent());
			
			blog.setCreateDate(oldBlog.getCreateDate());//?
			
			blog.setIsDisplay(blogInfo.getIsDisplay());
			blog.setStatus(blogInfo.getStatus());
			blog.setTitle(blogInfo.getTitle());
			blog.setViewCount(blogInfo.getViewCount());
			
			blog.setUser(userAccountService.findByUsernameOrEmail(blogInfo.getCreateBy()));

			return ResponseEntity.ok(blogService.createBlogs(blog));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.noContent().build();
		}
	}
}
