package edu.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping({"/", "/home/index"})
    public String home(Model model){
        model.addAttribute("pageTitle", "Home");
        return "home/index";
    }

    @RequestMapping(value = "/menu")
    public String listProduct(Model model){
        model.addAttribute("pageTitle", "Menu");
        return "product/list";
    }
    @RequestMapping(value = "/stories")
    public String blog(Model model){
        model.addAttribute("pageTitle", "Stories");
        return "blog/list";
    }
    @RequestMapping(value = "/reservation")
    public String reservation(Model model){
        model.addAttribute("pageTitle", "Reservation");
        return "reservation/reservation";
    }
}
