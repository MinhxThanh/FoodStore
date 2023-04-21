package edu.home.service.impl;

import edu.home.common.entity.RegisterUser;
import edu.home.entity.User;
import edu.home.repository.UserRepository;
import edu.home.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

}
