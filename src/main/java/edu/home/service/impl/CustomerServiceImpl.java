package edu.home.service.impl;

import edu.home.entity.Customer;
import edu.home.repository.CustomerRepository;
import edu.home.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository dao;
    @Override
    public Customer findByEmail(String email) {
        return dao.findById(email).get();
    }

    @Override
    public void changePasswordByEmail(String email, String password) {
        dao.updatePasswordByEmail(password, email);
    }

    @Override
    public Customer create(Customer customer) {
        return dao.save(customer);
    }

    @Override
    public Customer findByEmailKey(String email) {
        return dao.findByEmail(email);
    }
}

