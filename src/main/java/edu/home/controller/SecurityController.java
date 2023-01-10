package edu.home.controller;

import edu.home.common.create.InfoCustomer;
import edu.home.common.entity.MailInfoCustomer;
import edu.home.common.entity.RegisterCustomer;
import edu.home.entity.Customer;
import edu.home.service.CustomerService;
import edu.home.service.MailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@Controller
@RequestMapping(value = "security")
public class SecurityController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private InfoCustomer infoCustomer;
    @Autowired
    private MailerService mailerService;

    @RequestMapping(value = "login/form")
    public String loginForm(Model model){
        model.addAttribute("pageTitle", "Sign In");
        model.addAttribute("error", "Please login!");
        return "security/login";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("registerCustomer", new RegisterCustomer());
        model.addAttribute("pageTitle", "Register");
        return "security/register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String registerCreate(Model model, RegisterCustomer registerCustomer){
        if (customerService.findByEmail(registerCustomer.getEmail()) != null){
            model.addAttribute("error", "Email has been taken!, Please try with other Email!");
            return "security/register";
        }
        System.out.println("Email: " + registerCustomer.getEmail());
        infoCustomer.createCustomer(registerCustomer.getEmail(), registerCustomer.getUsername(), passwordEncoder.encode(registerCustomer.getPassword()), registerCustomer.getFullname());
        return "redirect:/security/login/form";
    }

    @GetMapping(value = "forgotPassword")
    public String forgotPassword(){
        return "security/forgotPassword";
    }
    @PostMapping(value = "forgotPassword")
    public String forgotPasswordSendEmail(@RequestParam("email") String email, Model model) throws MessagingException {
        Customer customer = customerService.findByEmail(email);
        if (customer == null)
            model.addAttribute("error", "This email don't register");
        else {
            MailInfoCustomer mail = new MailInfoCustomer();
            mail.setTo(customer.getEmail());
            mail.setUsername(customer.getUsername());
            mail.setSubject("Reset Your Password");
            mailerService.sendMailForgotPassword(mail);
            model.addAttribute("message", "We had send link for you reset password to this email");
        }
        return "security/forgotPassword";
    }

    @GetMapping(value = "forgotPassword/change/{email}")
    public String changePassword(Model model, @PathVariable("email") String email){
        model.addAttribute("emailAction", email);
        return "security/changePassword";
    }

    @PostMapping(value = "forgotPassword/change/{email}")
    public String changePasswordByEmail(@PathVariable("email") String email,
                                        @RequestParam("password") String password, Model model){
        try {
            customerService.changePasswordByEmail(email, passwordEncoder.encode(password));
            model.addAttribute("message", "Change Password successfully!");
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error", "Please reload this page and try again!");
        }
        return "security/changePassword";
    }
}
