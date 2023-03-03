package edu.home.service;

import edu.home.common.entity.RegisterUser;
import edu.home.entity.User;

import java.util.List;

public interface UserAccountService {
    User findByUsernameOrEmail(String key);

    User createUserAccount(User user);

    List<User> findAll();
}
