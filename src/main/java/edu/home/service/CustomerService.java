package edu.home.service;

import java.util.List;

import edu.home.entity.Customer;

public interface CustomerService {
    Customer findByEmail(String email);

    void changePasswordByEmail(String email, String password);

    Customer create(Customer customer);

    Customer findByEmailKey(String email);

    List<Customer> findAll();

    Customer findByCustomerEmail(String remoteUser);
    
    Customer update(Customer customer);
}
