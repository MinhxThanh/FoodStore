package edu.home.common.create;

import edu.home.entity.Customer;
import edu.home.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InfoCustomer {
    @Autowired
    private CustomerService customerService;

    public Customer createCustomer(String email, String username, String password, String fullname) {

        Customer customer = new Customer();

        customer.setEmail(email);
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setFullname(fullname);
        customer.setAvatar("/");
        customer.setBirthday(new Date());
        customer.setCode("null");
        customer.setCreateDate(new Date());
        customer.setGender(true);
        customer.setDisplay(true);
        customer.setRememberToken("null");
        customer.setStatus(1);

        customerService.create(customer);
        return customer;
    }
}
