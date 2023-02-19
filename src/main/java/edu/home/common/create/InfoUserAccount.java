package edu.home.common.create;

import edu.home.common.entity.RegisterUser;
import edu.home.entity.User;
import edu.home.entity.UserRole;
import edu.home.service.RoleService;
import edu.home.service.UserAccountService;
import edu.home.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InfoUserAccount {
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;

    public User createUser(RegisterUser registerUser){
        try {
            User user = new User();
            user.setUsername(registerUser.getUsername());
            user.setEmail(registerUser.getEmail());
            user.setPassword(registerUser.getPassword());
            user.setFullname("..");
            user.setGender(true);
            user.setPhone("00");
            user.setBirthday(new Date());
            user.setAvatar("undraw_profile.svg");
            user.setAddress("Your address");
            user.setRememberToken("token");
            user.setStatus(1);
            user.setCreateDate(new Date());
            user.setCreateBy(101);
            user.setDisplay(true);

            User user1 = userAccountService.createUserAccount(user);
            Thread.sleep(200);
            UserRole userRole = new UserRole();
            userRole.setUser(user1);
            userRole.setRole(roleService.findById(Long.valueOf(3)));
            userRoleService.create(userRole);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
