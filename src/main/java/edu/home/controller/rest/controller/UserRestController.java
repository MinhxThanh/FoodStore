package edu.home.controller.rest.controller;

import edu.home.entity.User;
import edu.home.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "rest/user")
public class UserRestController {
    @Autowired
    private UserAccountService userAccountService;

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
