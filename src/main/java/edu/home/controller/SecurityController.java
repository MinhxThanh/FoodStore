package edu.home.controller;

import edu.home.common.create.InfoCustomer;
import edu.home.common.entity.CartItem;
import edu.home.common.entity.MailInfoCustomer;
import edu.home.common.entity.MailInfoWelcome;
import edu.home.common.entity.RegisterCustomer;
import edu.home.entity.Customer;
import edu.home.service.CustomerService;
import edu.home.service.MailerService;
import edu.home.service.SessionService;
import edu.home.service.UserService;
import net.bytebuddy.description.method.MethodDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    @Autowired
    private UserService userService;
    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "login/form", method = RequestMethod.GET)
    public String loginForm(Model model){
        model.addAttribute("pageTitle", "Sign In");
        model.addAttribute("error", "Please login!");
        return "security/login";
    }

    @RequestMapping(value = "login/success")
    public String loginSuccess(Model model){
        model.addAttribute("pageTitle", "Sign In");
        model.addAttribute("message", "Login Successfully!");
        return "security/login";
    }

    @RequestMapping(value = "logout/success")
    public String logout(Model model){
        model.addAttribute("pageTitle", "Sign Out");
        model.addAttribute("message", "Logout successfully!");
        return "security/login";
    }

    @RequestMapping(value = "login/error")
    public String loginFail(Model model){
        model.addAttribute("pageTitle", "Sign In");
        model.addAttribute("error", "Incorrect username or password!");
        return "security/login";
    }

    @RequestMapping(value = "unauthorized")
    public String unauthorized(Model model){
        model.addAttribute("pageTitle", "Sign In");
        model.addAttribute("error", "You account don't unauthorized!");
        return "security/login";
    }

    @RequestMapping(value = "oauth2Login/success")
    public String oauth2(OAuth2AuthenticationToken oauth2) throws MessagingException {
        userService.loginFromOAuth2(oauth2);
        return "forward:/security/login/success";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("registerCustomer", new RegisterCustomer());
        model.addAttribute("pageTitle", "Register");
        return "security/register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String registerCreate(Model model, RegisterCustomer registerCustomer) throws MessagingException {
        if (customerService.findByEmailKey(registerCustomer.getEmail()) != null){
            model.addAttribute("error", "Email has been taken!, Please try with other Email!");
            return "security/register";
        }
        System.out.println("Email: " + registerCustomer.getEmail());

        infoCustomer.createCustomer(registerCustomer.getEmail(), passwordEncoder.encode(registerCustomer.getPassword()), registerCustomer.getLastName(), registerCustomer.getFirstName(), "/assets/images/logo-customer.png");

        MailInfoWelcome infoWelcome = new MailInfoWelcome();
        infoWelcome.setTo(registerCustomer.getEmail());
        infoWelcome.setFullname(registerCustomer.getFirstName() + " " + registerCustomer.getLastName());
        infoWelcome.setSubject("Well come you to Food Store!");
        infoWelcome.setPassword(registerCustomer.getPassword());
        infoWelcome.setImage("/assets/images/logo-customer.png");
        mailerService.sendMailWelcome(infoWelcome);
        return "redirect:/security/login/form";
    }

    @GetMapping(value = "forgotPassword")
    public String forgotPassword(){
        return "security/forgotPassword";
    }
    @PostMapping(value = "forgotPassword")
    public String forgotPasswordSendEmail(@RequestParam("email") String email, Model model) throws MessagingException {
        Customer customer = customerService.findByEmailKey(email);
        if (customer == null)
            model.addAttribute("error", "This email don't register");
        else {
            MailInfoCustomer mail = new MailInfoCustomer();
            mail.setTo(customer.getEmail());
            mail.setUsername(customer.getFullname());
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
