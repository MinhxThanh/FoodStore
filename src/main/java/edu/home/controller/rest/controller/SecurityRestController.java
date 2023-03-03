package edu.home.controller.rest.controller;

import edu.home.common.create.InfoUserAccount;
import edu.home.common.entity.AuthRequest;
import edu.home.common.entity.AuthResponse;
import edu.home.common.entity.RegisterUser;
import edu.home.entity.User;
import edu.home.jwt.JwtTokenUtil;
import edu.home.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "rest/auth")
public class SecurityRestController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private InfoUserAccount infoUserAccount;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request){
        try {
            User user = userAccountService.findByUsernameOrEmail(request.getEmail());
            System.out.println("Name: " + user.getUsername());
//            if(!user.getUserRoles().stream().anyMatch(item -> item.getRole().getId() == 1))
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//            );
            String accessToken = jwtTokenUtil.generateAccessToken(request.getEmail());
            AuthResponse response = new AuthResponse(request.getEmail(), accessToken, user.getUsername(), user.getAvatar(), user);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping(value = "register")
    public ResponseEntity<?> register(@RequestBody RegisterUser user){
        try {
            User userAccount = infoUserAccount.createUser(user);
            return ResponseEntity.ok(userAccount);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}

