package com.scholarship.demo.api;

import lombok.Data;

@Data
public class LoginResponse {

    private String account;
    private String userName;
    private String userType;
}
