package edu.home.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.home.entity.User;

public interface UserAccountService {
    User findByUsernameOrEmail(String key);

    User createUserAccount(User user);

    List<User> findAll();

    User updateUser(User user);

    List<User> getAllUserHaveFood();

    User findByUsername(Optional<String> nameShop);
    
    Page<User> findListFoodByUser(String key, Pageable pageable);
}
