package edu.home.service;

import edu.home.entity.Role;

import java.util.List;

public interface RoleService {
    Role findById(Long i);

    List<Role> findAll();
}
