package edu.home.controller.rest.controller;

import edu.home.entity.Blog;
import edu.home.entity.CategoryBlog;
import edu.home.entity.User;
import edu.home.repository.UserRepository;
import edu.home.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "rest/user")
public class UserRestController {
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "getAll")
    public ResponseEntity<?> getAllUser() {
        try {
            return ResponseEntity.ok(userAccountService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("getUser/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        try {
            return ResponseEntity.ok(userAccountService.findByUsernameOrEmail(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("updateUser")
    public ResponseEntity<?> updateUser(@RequestBody User requestUser){
        try {
            User user = userAccountService.findByUsernameOrEmail(requestUser.getUsername());
            User newUser = new User();
            newUser.setUsername(user.getUsername());
            newUser.setCreateBy(user.getCreateBy());
            newUser.setCreateDate(user.getCreateDate());
            newUser.setStatus(user.getStatus());
            newUser.setId(user.getId());
            newUser.setRememberToken(user.getRememberToken());
            newUser.setEmail(user.getEmail());
            newUser.setDisplay(user.isDisplay());
            // trường thay đổi
            newUser.setPassword(requestUser.getPassword());
            newUser.setFullname(requestUser.getFullname());
            newUser.setGender(requestUser.isGender());
            newUser.setPhone(requestUser.getPhone());
            newUser.setBirthday(requestUser.getBirthday());
            newUser.setAvatar(requestUser.getAvatar());
            newUser.setAddress(requestUser.getAddress());
            return ResponseEntity.ok(userAccountService.updateUser(newUser));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("searchByEmailOrUsername/{key}")
    public ResponseEntity<?> findByEmailOrUsername(@PathVariable("key") String key){
        try {
            User user = userAccountService.findByUsernameOrEmail(key);
            if (user != null)
                return ResponseEntity.ok(user);
            else
                return ResponseEntity.ok(new User());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
