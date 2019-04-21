package com.scholarship.demo.api;

import lombok.Data;

@Data
public class Key {

    /**
     * 管理员账号，用来判断是校级还是院级
     */
    private String account;
    private String key;
    private String level;
}
