package edu.home.service;

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
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email){
        Customer customer = customerService.findByEmail(email);
        try {

            System.out.println("loadUserByUsername Username: " + customer.getEmail());
            System.out.println("Password: " + customer.getPassword());

            //Create userDetail from account
            String password = customer.getPassword();

            return User.withUsername(email)
                    .password(password).build();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Exception Username 3: " +email);
            throw new UsernameNotFoundException(email + "not found!");
        }
    }

}
