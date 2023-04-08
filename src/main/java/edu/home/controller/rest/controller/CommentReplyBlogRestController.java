package edu.home.controller.rest.controller;

import edu.home.common.entity.CommentBlogRequest;
import edu.home.common.entity.CommentReplyBlogRequest;
import edu.home.entity.CommentReplyBlog;
import edu.home.entity.Customer;
import edu.home.service.BlogService;
import edu.home.service.CommentBlogService;
import edu.home.service.CommentReplyBlogService;
import edu.home.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "rest/commentReplyBlog")
public class CommentReplyBlogRestController {
    @Autowired
    private CommentReplyBlogService replyBlogService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CommentBlogService commentBlogService;
    @Autowired
    private BlogService blogService;

    @GetMapping(value = "findAllByBlogId/{id}")
    public ResponseEntity<?> findAllByBlogId(@PathVariable("id") Long blogId) {
        try {
            return ResponseEntity.ok(replyBlogService.findAllByBlogId(blogId));
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "create")
    public ResponseEntity<?> create(@RequestBody CommentReplyBlogRequest replyBlogRequest, HttpServletRequest request) {
        try {
            Customer customer = customerService.findByEmailKey(request.getRemoteUser());
            CommentReplyBlog item = new CommentReplyBlog();
            item.setTitle(customer.getLastName() + " " + customer.getFirstName());
            item.setBlog(blogService.findBlogsById(replyBlogRequest.getBlogId()));
            item.setCustomer(customer);
            item.setCommentsBlog(commentBlogService.findById(replyBlogRequest.getCommentId()));
            item.setContent(replyBlogRequest.getContent());
            item.setCreateDate(new Date());
            item.setUpdateDate(new Date());
            item.setStatus(1);
            item.setDisplay(true);
            return ResponseEntity.ok(replyBlogService.create(item));
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(value = "delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        replyBlogService.delete(id);
    }
}
