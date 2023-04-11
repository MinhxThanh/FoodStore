package edu.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "customer")
public class CustomerController {
	
	@RequestMapping(value = "profile/account")
    public String profile(Model model){
		model.addAttribute("accPage", "active");
        model.addAttribute("pageTitle", "Profile");
        return "customer/profile";
    }
	
	@RequestMapping(value = "profile/address")
    public String address(Model model){
		model.addAttribute("addressPage", "active");
        model.addAttribute("pageTitle", "Profile");
        return "customer/profile";
    }
}
