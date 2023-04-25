package edu.home.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.home.entity.User;
import edu.home.repository.UserRepository;
import edu.home.service.UserAccountService;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    private UserRepository dao;

    @Override
    public User findByUsernameOrEmail(String key) {
        return dao.findUserByUsernameOrEmail(key);
    }

    @Override
    public User createUserAccount(User user) {
        return dao.save(user);
    }

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public User updateUser(User user) {
        return dao.save(user);
    }

    @Override
    public List<User> getAllUserHaveFood() {
        return dao.getAllUserHaveFood();
    }

    @Override
    public User findByUsername(Optional<String> nameShop) {
        return dao.findByUsername(nameShop);
    }

    @Override
    public Page<User> findListFoodByUser(String key, Pageable pageable) {
    	System.out.println(key);
        return dao.findListFoodByUser(key, pageable);
    }
}
