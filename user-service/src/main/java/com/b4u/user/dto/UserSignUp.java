package com.b4u.user.dto;

import lombok.Data;

@Data
public class UserSignUp {
    private String username;
    private String password;
    private String email;
    private String phone;
}
