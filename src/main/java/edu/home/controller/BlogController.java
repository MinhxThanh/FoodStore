package edu.home.controller;

import edu.home.entity.*;
import edu.home.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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


    @GetMapping({ "all", "{category}" })
    public String PageBlog(Model model, 
    		@RequestParam("p") Optional<Integer> p, 
    		@PathVariable(value = "category") Optional<String> cid,
			@RequestParam(value = "size") Optional<Integer> size,
    		@RequestParam(value = "sortBy") Optional<String> sort) {
    	boolean up = sort.orElse("").contains("Up");
		Sort sortOption = Sort.by(up ? Direction.ASC : Direction.DESC
				, sort.orElse("title").replace("Down","").replace("Up", ""));
        List<Blog> top3 = blogService.topBaBlog();
        Pageable pageable = PageRequest.of(p.isPresent() ? p.get()-1 : 0, size.orElse(4), sortOption);
        Optional<Long> cate_id = Optional.ofNullable(null);
        if(cid.isPresent()) {
        	cate_id = Optional.ofNullable(cateService.getByName(cid.get()).getId());
    		Page<Blog> blogPage1 = blogService.findByCategoryId(cate_id, pageable);
			model.addAttribute("size", size.orElse(4));
			model.addAttribute("sortBy",sort.orElse("idDown"));
    		model.addAttribute("cid", cid.orElse(""));
    		model.addAttribute("listBlog", blogPage1);
            model.addAttribute("listCate", cateService.findAllCategory());
            model.addAttribute("top3", top3);
    	}else {
	        Page<Blog> blogPage = blogRepo.findAll(pageable);
			model.addAttribute("size", size.orElse(4));
			model.addAttribute("sortBy",sort.orElse("idDown"));
			model.addAttribute("cid", cid.orElse(""));
	        model.addAttribute("listBlog", blogPage);
	        model.addAttribute("listCate", cateService.findAllCategory());
	        model.addAttribute("top3", top3);
    	}
        return "blog/list";
    }

    @GetMapping("list")
    public String searchBlog(Model model, @Param("keyword") String keyword, @RequestParam("p") Optional<Integer> p) {
        Pageable pageable = PageRequest.of(p.isPresent() ? p.get()-1 : 0, 4,Sort.by("id"));
        Page<Blog> blogSearch = blogService.searchBlogs(keyword, pageable);
        blogSearch.forEach(item -> {
            System.out.println(item.getTitle() + " dwdqwdqw");
        });
        List<Blog> top3 = blogService.topBaBlog();
        model.addAttribute("top3", top3);
        model.addAttribute("listBlog", blogSearch);
        model.addAttribute("keyword", keyword);
        model.addAttribute("listCate", cateService.findAllCategory());
        return "blog/list";
    }

    @RequestMapping("detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        Blog item = blogService.findBlogsById(id);
        List<CategoryBlog> categoryBlog = categoryBlogService.findAllByBlogId(id);

        categoryBlog.forEach(t -> {
            System.out.println("name: " + t.getCategory().getName());
        });

        List<Blog> top3 = blogService.topBaBlog();
        model.addAttribute("top3", top3);
        model.addAttribute("item", item);
        model.addAttribute("categories", categoryBlog);
        blogService.updateViewCount(id, item.getViewCount() + 1);

        return "blog/detail-blog";
    }
    
    @ModelAttribute("sortList")
	public Map<String, String> get() {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("Mới nhất", "idDown");
		map.put("Cũ nhất", "idUp");
		map.put("A-Z", "titleUp");
		map.put("Z-A", "titleDown");
		return map;
	}
}