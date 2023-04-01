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

    public Customer createCustomer(String email, String password, String lastName, String firstName, String avatar) {

        Customer customer = new Customer();

        customer.setEmail(email);
        customer.setPassword(password);
        customer.setLastName(lastName);
        customer.setFirstName(firstName);
        customer.setFullname(lastName + " " + firstName);
        customer.setAvatar(avatar);
        customer.setGender(true);
        customer.setBirthday(new Date());
        customer.setRememberToken("null");
        customer.setStatus(1);
        customer.setCreateDate(new Date());
        customer.setDisplay(true);

        customerService.create(customer);
        return customer;
    }
}
