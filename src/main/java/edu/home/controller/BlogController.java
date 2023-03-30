package edu.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import edu.home.entity.Blog;
import edu.home.entity.Comment;
import edu.home.entity.CommentsBlog;
import edu.home.entity.Customer;
import edu.home.entity.User;
import edu.home.repository.BlogRepository;
import edu.home.repository.UserRepository;
import edu.home.service.BlogService;
import edu.home.service.CategoryService;
import edu.home.service.CustomerService;
import edu.home.service.UserAccountService;

@Controller
@RequestMapping(value = "stories")
public class BlogController {
	
	@Autowired
  BlogService blogService;
	@Autowired
	CategoryService cateService;
	@Autowired
	BlogRepository blogRepo;
	@Autowired
	UserAccountService userSer;
	@Autowired
	CustomerService cteSer;
	
	@Autowired
	CustomerService cusSer;
	
	@GetMapping(value = "")
	public String PageBlog(Model model,
			
			@RequestParam("p") Optional<Integer> p 
			)
	 {
		List<Blog> top3 = blogService.topBaBlog();
		int size = 8;
		Pageable pageable = PageRequest.of(p.orElse(0), 2, Sort.by("id"));
		Page<Blog> blogPage = blogRepo.findAll(pageable);
//		blogPage.stream().forEach(item-> {
//			item.getTitle();
//			System.out.print("vinh"+ item.getTitle());
//		});
      model.addAttribute("listBlog", blogPage);
     
      model.addAttribute("listCate", cateService.findAllCategory());
      model.addAttribute("top3", top3);
//      model.addAttribute("loaiBLog", cateService.findAllCategory());
//  System.out.println(blogService.findAllBlog().get(0).getImageBlogs().get(0).getImageName()+"hinh");
//      System.out.println("33333333333333333333333333");
 
      return "blog/list";}
//	public String findAllBlog(Model model) {
//		model.addAttribute("listBlog", blogService.findAllBlog());
//		 return "blog/list";
//	}
	@GetMapping("list")
	public String searchBlog(Model model,
			@Param("keyword") String keyword) {
		Page<Blog> blogSearch = blogService.searchBlogs(keyword);
		blogSearch.forEach(item->{
			System.out.println(item.getTitle() + " dwdqwdqw");
		});
		model.addAttribute("listBlog", blogSearch);
		model.addAttribute("keyword", keyword);
		 return "blog/list";
	}
	
	
	
	@RequestMapping("detail/{id}")
	public String detail(Model model, @PathVariable("id") Long id, HttpServletRequest request) {
		Comment cmt = new Comment();
		Blog item = blogService.findBlogsById(id);
//		String  a = request.getRemoteUser();
//		Customer user = cusSer.findByEmail(a);
//		   System.out.println("33333333333333333333333333" + user);
//		Feature feature = featureSer.createFeature(id, user);
//		
//		 Optional<Feature> featureOpt = featureService.findById(featureId);
//		 System.out.println("33333333333333333333333333" + feature.getId());
		model.addAttribute("feature", item );
		model.addAttribute("rootComment", cmt);
		model.addAttribute("item", item);	
//		model.addAttribute("commentId", "0");
		blogService.updateViewCount(id, item.getViewCount()+1);
		
		return "blog/detail-blog" ;
	}
	
//	@PostMapping("") // this maps to -> '/products/{productId}/features'
//	  public String createFeature( Customer user, @PathVariable Long productId) {
//	    Feature feature = featureSer.createFeature(productId);
//	    
//	    return "redirect:/products/"+productId+"/features/"+feature.getId();
//	    
//	  }
	
	
	@RequestMapping("categoryBlog/{categoryId}")
	public String categoriBlog(Model model, @PathVariable("categoryId") Long id) {
		 
		 model.addAttribute("listBlog", blogService.findBLogsByCategory(id));
//		  System.out.println(blogService.findAllBlog().get(0).getImageBlogs().get(0).getImageName()+"hinh");
//		      System.out.println("33333333333333333333333333");
		return "blog/list";
	}
	
//	@RequestMapping("createComment")
//	public String commentBlog(Model model,@Param("cmt") String cmt, @Param("idBlog") Long idBlog,@Param("commentId") Long commentId, HttpServletRequest request ) {
////			System.out.println("+c "+ cmt);
////			System.out.println("+d "+ idBlog);
//		try {
//		
//			CommentsBlog itemcmt = new CommentsBlog();
//			if(commentId == 0){
//				
//				itemcmt.setBlog(blogService.findBlogsById(idBlog));
//				String  a = request.getRemoteUser();
//				itemcmt.setContent(cmt);
//				itemcmt.setTitle(a);
//				itemcmt.setCreateDate(new Date());
//				itemcmt.setCustomer(cteSer.findByEmail(a));
//				itemcmt.setDisplay(true);
//				itemcmt.setStatus(1);
////				itemcmt.setCommentReplyBlogs(null);
//				itemcmt.setUpdateDate(new Date());
//				commentService.createCmt(itemcmt);	
//				model.addAttribute("ListCommentBlog", itemcmt);
//			}else{
//				Feature itemreply = new Feature();
//				itemreply.setBlog(blogService.findBlogsById(idBlog));
//				String  a = request.getRemoteUser();
//				itemreply.setCommentsBlog(commentService.findById(commentId));
//				itemreply.setContent(cmt);
//				itemreply.setCreateDate(new Date());
//				itemreply.setCustomer(cteSer.findByEmail(a));
//				itemreply.setDisplay(true);
//				itemreply.setStatus(1);
//				itemreply.setTitle(a);
//				itemreply.setUpdateDate(new Date());
//				cmtRepRepo.save(itemreply);	
//				model.addAttribute("ListCommentBlogReply", itemreply);
//				System.out.print(itemreply +"qweenqwjenjqwneljqwnelnqwleqwnln");
//			}} catch (Exception e) {
//			System.out.println(e);
//			// TODO: handle exception
//		}
//			
//		return "redirect:/stories/detail/" +idBlog;
//	}
	
//	@RequestMapping("updateComment")
//	public String UpcommentBlog(Model model,@Param("cmt") String cmt, @Param("idBlog") Long idBlog,  @Param("commentId") Long commentId, HttpServletRequest request ) {
//		CommentsBlog item= commentService.findById(commentId);
//		item.setBlog(blogService.findBlogsById(idBlog));
//		String  a = request.getRemoteUser();
//		item.setContent(a);
//		item.setCreateDate(new Date());
//		item.setCustomer(cteSer.findByEmail(a));
//		item.setDisplay(true);
//		item.setStatus(1);
//		item.setTitle(cmt);
//		item.setUpdateDate(new Date());
//		item = commentService.createCmt(item);
//		model.addAttribute("ListCommentBlog", item);
//		Feature item2 = cmtReply.findById(commentId);
//		item2.setBlog(blogService.findBlogsById(idBlog));
//		item2.setCommentsBlog(item);
//		item2.setContent(a);
//		item2.setCreateDate(new Date());
//		item2.setCustomer(cteSer.findByEmail(a));
//		item2.setDisplay(true);
//		item2.setStatus(1);
//		item2.setTitle(cmt);
//		item2.setUpdateDate(new Date());
//		item2 = cmtRepRepo.save(item2);
//		model.addAttribute("ListCommentBlogReply", item2);
//
//		return "redirect:/stories/detail/" +idBlog;
//	}
//	
//	@RequestMapping("deleteComment/{blogId}/{cmtId}")
//	public String DeletecommentBlog(Model model,@PathVariable("blogId") Long blogId, @PathVariable("cmtId") Long cmtId, HttpServletRequest request ) {
//		try {
//			Blog blog = blogService.findBlogsById(blogId);
//			if(blog.getId() == blogId) {
//				cmtRepo.deleteById(cmtId);
//			}else { System.out.print("vui lòng nhập đúng id");
//			}	
//		} catch (Exception e) {
//			System.out.println(e);
//			// TODO: handle exception
//		}
//			
//		return "redirect:/stories/detail/" +blogId;
//	}
//	@RequestMapping("deleteCommentReplyBlog/{blogId}/{cmtId}")
//	public String deleteCommentReplyBlog(Model model,@PathVariable("blogId") Long blogId, @PathVariable("cmtId") Long cmtId, HttpServletRequest request ) {
//		try {
//			Blog blog = blogService.findBlogsById(blogId);
//			if(blog.getId() == blogId) {
//				cmtRepRepo.deleteById(cmtId);
//			}else { System.out.print("vui lòng nhập đúng id");
//			}	
//		} catch (Exception e) {
//			System.out.println(e);
//			// TODO: handle exception
//		}
//		
//		return "redirect:/stories/detail/" +blogId;
//	}
//	
//	@RequestMapping("repplyCommentId/{blogId}/{cmtId}")
//	public String getButtonCmt(Model model, @PathVariable("blogId") Long blogId,
//			@PathVariable("cmtId") Long cmtId
//			) {
//		Blog item = blogService.findBlogsById(blogId);
//		List<CommentsBlog> cmtId2 = cmtRepo.findAllById(blogId);
//		List<Feature> cmtRep = cmtReply.findAllCmtreplyBlog(blogId);
////		model.addAttribute("ListCommentBlog", commentService.findAllComemnt());
//		model.addAttribute("ListCommentBlog", cmtId2);
//		model.addAttribute("ListCommentBlogReply", cmtRep);
//		model.addAttribute("item", item);	
//		model.addAttribute("commentId", cmtId);
//		
//		return "blog/detail-blog";
//		
//	}
	
}