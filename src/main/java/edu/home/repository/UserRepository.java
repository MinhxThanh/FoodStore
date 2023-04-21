package edu.home.repository;

import edu.home.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.username =?1 or u.email = ?1")
    User findUserByUsernameOrEmail(String key);

    @Query("select u from User u left join Food f on f.user.id = u.id where u.isDisplay = true " +
            "group by u.id, u.username, u.password, u.fullname, u.gender, u.phone, u.email, u.birthday, u.avatar, u.address, u.rememberToken, u.status, u.createDate, u.createBy, u.isDisplay, u.cityProvince, u.openTime, u.costRestaurant, u.slogan " +
            "having count(f) > 0")
    List<User> getAllUserHaveFood();

    @Query("select u from User u where  u.username = ?1")
    User findByUsername(Optional<String> nameShop);
}