package com.scholarship.demo.model;

import lombok.Data;

@Data
public class Admin {

    private int id;
    private String account;
    private String passWord;
    private String userName;
    private String level;
    private String college;
    private String isApply;
}

