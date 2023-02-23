package edu.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.home.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class HomeController {
    @Autowired
	FoodService foodService;

    @RequestMapping({"/", "/home/index"})
    public String home(Model model){
        model.addAttribute("pageTitle", "Home");
        foodService.listfood(1).stream().forEach(i ->{
        		System.out.println("list: " + i.getImageName());
        });
        model.addAttribute("imageName", foodService.listfood(1));
        model.addAttribute("imageName2", foodService.listfood(2));
        model.addAttribute("imageName3", foodService.listfood(3));
        model.addAttribute("imageName4", foodService.listfood(4));
        model.addAttribute("imageName5", foodService.listfood(5));
        model.addAttribute("imageName6", foodService.listfood(6));
        
        return "home/index";
    }

    @RequestMapping(value = "/menu")
    public String listProduct(Model model){
        model.addAttribute("pageTitle", "Menu");
        return "product/list";
    }
    @RequestMapping(value = "/product/detail")
    public String productDetailTest(Model model){
        model.addAttribute("pageTitle", "Menu");
        return "product/detail";
    }
    @RequestMapping(value = "/stories")
    public String blog(Model model){
        model.addAttribute("pageTitle", "Stories");
        return "blog/list";
    }
    @RequestMapping(value = "/contact")
    public String contact(Model model){
        model.addAttribute("pageTitle", "Contact");
        return "home/contact";
    }
    @RequestMapping(value = "/cart")
    public String shoppingCart(Model model){
        model.addAttribute("pageTitle", "Shopping Cart");
        return "cart/shopping-cart";
    }
}
