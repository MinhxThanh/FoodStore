package edu.home.service.impl;

import edu.home.entity.User;
import edu.home.repository.UserRepository;
import edu.home.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    
//    Giàu
    @Override
	public User findById(Long id) {
		return dao.findById(id).get();
	}

}
