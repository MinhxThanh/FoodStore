package edu.home.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class RegisterCustomer {
    @Id
    private String email;
    private String password;
    private String passwordConfirm;
    private String firstName;
    private String lastName;
}
