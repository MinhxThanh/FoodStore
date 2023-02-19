package edu.home.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AuthRequest {

    @Length(min = 3)
    private String email;
    @Length(min = 5)
    private String password;
}