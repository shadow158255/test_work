package com.example.my.product.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.my.product.pojo.base.BaseEntity;
import lombok.Data;

@Data
@TableName("user")
public class User extends BaseEntity {

    private static final Long serialVersionUID = 1L;
    @TableField("password")
    private String password;
    @TableField("username")
    private String username;
    @TableField("is_frozen")
    private String isFrozen;

}
