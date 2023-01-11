package com.example.my.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.my.product.pojo.entity.User;


public interface UserService extends IService<User> {
    User getUserByUsername(String username);

    void login(User user);

    void reg(User user);
}
