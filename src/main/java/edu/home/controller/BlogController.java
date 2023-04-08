package edu.home.controller;

import edu.home.entity.*;
import edu.home.service.*;
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

import edu.home.repository.BlogRepository;
import edu.home.repository.UserRepository;

@Controller
@RequestMapping(value = "stories")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService cateService;
    @Autowired
    private BlogRepository blogRepo;
    @Autowired
    private CategoryBlogService categoryBlogService;


    @GetMapping(value = "")
    public String PageBlog(Model model, @RequestParam("p") Optional<Integer> p) {
        List<Blog> top3 = blogService.topBaBlog();
        int size = 8;
        Pageable pageable = PageRequest.of(p.orElse(0), 2, Sort.by("id"));
        Page<Blog> blogPage = blogRepo.findAll(pageable);

        model.addAttribute("listBlog", blogPage);
        model.addAttribute("listCate", cateService.findAllCategory());
        model.addAttribute("top3", top3);

        return "blog/list";
    }

    @GetMapping("list")
    public String searchBlog(Model model,
                             @Param("keyword") String keyword) {
        Page<Blog> blogSearch = blogService.searchBlogs(keyword);
        blogSearch.forEach(item -> {
            System.out.println(item.getTitle() + " dwdqwdqw");
        });
        model.addAttribute("listBlog", blogSearch);
        model.addAttribute("keyword", keyword);
        return "blog/list";
    }


    @RequestMapping("detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        Blog item = blogService.findBlogsById(id);
        List<CategoryBlog> categoryBlog = categoryBlogService.findAllByBlogId(id);

        categoryBlog.forEach(t -> {
            System.out.println("name: " + t.getCategory().getName());
        });

        model.addAttribute("item", item);
        model.addAttribute("categories", categoryBlog);
        blogService.updateViewCount(id, item.getViewCount() + 1);

        return "blog/detail-blog";
    }

}