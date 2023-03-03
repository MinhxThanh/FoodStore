package edu.home.controller.rest.controller;

import edu.home.entity.UserRole;
import edu.home.service.RoleService;
import edu.home.service.UserAccountService;
import edu.home.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/authority")
public class AuthorityRestController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping(value = "getAllRoles")
    public ResponseEntity<?> getAllRoles(){
        try {
            return ResponseEntity.ok(roleService.findAll());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "getAllUserAccounts")
    public ResponseEntity<?> getAllUserAccounts(){
        try {
            return ResponseEntity.ok(userAccountService.findAll());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "getAllUserRole")
    public ResponseEntity<?> getAllUserRole(){
        try {
            return ResponseEntity.ok(userRoleService.findAll());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "createUserRole")
    public ResponseEntity<?> create(@RequestBody UserRole userRole){
        try {

            return ResponseEntity.ok(userRoleService.create(userRole));
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(value = "deleteById/{id}")
    public void delete(@PathVariable("id") Long id){
        userRoleService.delete(id);
    }
}
