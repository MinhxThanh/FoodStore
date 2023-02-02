package edu.home.service;

import edu.home.entity.Customer;

public interface CustomerService {
    Customer findByEmail(String email);

    void changePasswordByEmail(String email, String password);

    Customer create(Customer customer);

    Customer findByEmailKey(String email);
}
