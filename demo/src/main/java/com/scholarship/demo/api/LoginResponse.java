package com.scholarship.demo.api;

import lombok.Data;

@Data
public class LoginResponse {

    private String userName;
    private String password;
    private String userType;
}
