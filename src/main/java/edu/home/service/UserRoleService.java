package edu.home.service;

import edu.home.entity.UserRole;

import java.util.List;

public interface UserRoleService {
    UserRole create(UserRole userRole);

    List<UserRole> findAll();

    void delete(Long id);
}
