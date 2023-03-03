package edu.home.service.impl;

import edu.home.entity.UserRole;
import edu.home.repository.UserRepository;
import edu.home.repository.UserRoleRepository;
import edu.home.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository dao;

    @Override
    public UserRole create(UserRole userRole) {
        return dao.save(userRole);
    }

    @Override
    public List<UserRole> findAll() {
        return dao.findAll();
    }

    @Override
    public void delete(Long id) {
        dao.deleteById(id);
    }
}
