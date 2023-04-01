//package edu.home.service.impl;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import edu.home.entity.Blog;
//import edu.home.entity.CommentsBlog;
//import edu.home.repository.BlogRepository;
//import edu.home.repository.CommentsBlogRepository;
//import edu.home.service.CommentBlogService;
//
//@Service
//public class CommentBlogServiceImpl implements CommentBlogService {
//	@Autowired
//	BlogRepository blogRepo;
//	@Autowired
//	CommentsBlogRepository cmt;
//	
//	@Override
//	public List<CommentsBlog> findAllComemnt() {
//		// TODO Auto-generated method stub
//		 return cmt.findAll();
//	}
//
//	
//
//	@Override
//	public CommentsBlog createCmt(CommentsBlog BlogId) {
//		return cmt.save(BlogId);
//	}
//
//	@Override
//	public CommentsBlog updateCmt(CommentsBlog BlogId) {
//		return cmt.save(BlogId);
//	}
//
//	@Override
//	public void delete(Long id) {
//		// TODO Auto-generated method stub
//		cmt.deleteById(id);
//	}
//
//
//
//	@Override
//	public List<CommentsBlog> findAllById(Long BlogId) {
//		// TODO Auto-generated method stub
//		List<CommentsBlog> listCmt = cmt.findAllById(BlogId);;
//		return listCmt;
//	}
//
//
//	
//	@Override
//	public CommentsBlog findById(Long BlogId) {
//		// TODO Auto-generated method stub
//		return cmt.findById(BlogId).get();
//	}
//
//
//
//	
//
//
//
//
//}
