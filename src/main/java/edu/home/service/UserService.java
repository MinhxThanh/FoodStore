package edu.home.service;

import edu.home.common.create.InfoCustomer;
import edu.home.common.entity.MailInfoWelcome;
import edu.home.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private MailerService mailerService;
    @Autowired
    private InfoCustomer infoCustomer;

    @Override
    public UserDetails loadUserByUsername(String email){
        Customer customer = customerService.findByEmailKey(email);
        try {

            System.out.println("loadUserByUsername Username: " + customer.getEmail());
            System.out.println("Password: " + customer.getPassword());

            //Create userDetail from account
            String password = customer.getPassword();

            return User.withUsername(email)
                    .password(password).roles("GUEST").build();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Exception Username 3: " +email);
            throw new UsernameNotFoundException(email + "not found!");
        }
    }

    public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) throws MessagingException {
        String email = oauth2.getPrincipal().getAttribute("email");
        String password = oauth2.getPrincipal().getName();
        String firstName = oauth2.getPrincipal().getAttribute("given_name");
        String lastName = oauth2.getPrincipal().getAttribute("family_name");
        String image = oauth2.getPrincipal().getAttribute("picture");
        System.out.println("Login with: " + oauth2.getAuthorizedClientRegistrationId());

        if (lastName == null) {
            lastName = "last name!";
            System.out.println("Last name: " + lastName);
        }

//        String accessToken = "Bearer " +  jwtTokenUtil.generateAccessToken(email);
        if (customerService.findByEmailKey(email) != null){
            System.out.println("This email already exists in data!");
        }else {
            infoCustomer.createCustomer(email, passwordEncoder.encode(password), lastName, firstName, image);
            MailInfoWelcome infoWelcome = new MailInfoWelcome();
            infoWelcome.setTo(email);
            infoWelcome.setSubject("Well come you to Food Store!");
            infoWelcome.setFullname(firstName + " " + lastName);
            infoWelcome.setPassword(password);
            infoWelcome.setImage(image);
            mailerService.sendMailWelcome(infoWelcome);
        }

        UserDetails userDetails = User.withUsername(email)
                .password(passwordEncoder.encode(password)).roles("GUEST").build();
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
