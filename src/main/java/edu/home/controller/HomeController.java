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
        return "home/index";
    }

    @RequestMapping(value = "/menu")
    public String listProduct(Model model){
        model.addAttribute("listSale", foodService.getListSaleFood());
        model.addAttribute("listTop", foodService.getListTopNewFood());
        model.addAttribute("pageTitle", "Menu");
        return "product/list";
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
    public String shoppingCart(Model model) {
        model.addAttribute("pageTitle", "Shopping Cart");
        return "cart/shopping-cart";
    }
}
