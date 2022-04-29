package com.waazon.backend.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String role;
}
