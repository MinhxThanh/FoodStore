package edu.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "security")
public class SecurityController {

    @RequestMapping("login/form")
    public String loginForm(Model model){
        model.addAttribute("pageTitle", "Sign In");
        model.addAttribute("error", "Please login!");
        return "security/login";
    }
}
