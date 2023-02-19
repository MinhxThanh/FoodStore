package edu.home.repository;

import edu.home.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.username =?1 or u.email = ?1")
    User findUserByUsernameOrEmail(String key);
}