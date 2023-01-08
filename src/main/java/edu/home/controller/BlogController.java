package edu.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "stories")
public class BlogController {

    @RequestMapping(value = "detail")
    public String list(Model model){
        model.addAttribute("pageTitle", "Name Story");
        return "blog/detail-blog";
    }
}
