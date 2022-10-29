package com.example.my.product.security;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginPrincipal implements Serializable {
    /**
     * 当事人id
     */
    private Long id;
    /**
     * 当事人用户名
     */
    private String username;
}
