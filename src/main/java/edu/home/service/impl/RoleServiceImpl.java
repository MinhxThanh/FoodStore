package edu.home.service.impl;

import edu.home.entity.Role;
import edu.home.repository.RoleRepository;
import edu.home.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository dao;

    @Override
    public Role findById(Long i) {
        return dao.findById(i).get();
    }
}
