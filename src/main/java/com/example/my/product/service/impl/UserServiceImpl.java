package com.example.my.product.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.my.product.ex.ServiceException;
import com.example.my.product.mapper.UserMapper;
import com.example.my.product.pojo.entity.User;
import com.example.my.product.service.UserService;

import com.example.my.product.utils.MD5;
import com.example.my.product.web.ServiceCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUserByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        return baseMapper.selectOne(wrapper);

    }

    @Override
    public void login(User user) {
        int num = 0;
        User userByUsername = this.getUserByUsername(user.getUsername());
        if (userByUsername == null){
            throw new ServiceException(ServiceCode.ERR_ACCOUNT_NOTFOUND,"用户名不存在在！请重新输入！");
        }
        if (!MD5.encrypt(user.getPassword()).equals(userByUsername.getPassword())){
            num++;
            if (num > 3){
                User user1 = new User();
                user1.setIsFrozen("1");
                userMapper.insert(user1);
            }
            throw new ServiceException(ServiceCode.ERR_ACCOUNT_PASSWORD,"密码不正确");
        }
        if (userByUsername.getIsFrozen().equals("1")){
            throw new ServiceException(ServiceCode.ERR_FREEZE_AN_ACCOUNT,"您的账户已冻结，请联系管理员处理！");
        }
    }

    @Override
    public void reg(User user) {
        if (user.getUsername() == null || user.getPassword() == null){
            throw new ServiceException(ServiceCode.ERR_ACCOUNT_PASSWORD_NOT_NULL,"用户名或密码不能为空！");
        }
        User userByUsername = this.getUserByUsername(user.getUsername());
        if (!(userByUsername == null)){
            throw new ServiceException(ServiceCode.ERR_ACCOUNT_EXIST,"该用户已存在！请重新输入！");
        }
        User user1 = new User();
        user1.setPassword(MD5.encrypt(user.getPassword()));
        user1.setUsername(user.getUsername());
        userMapper.insert(user1);
    }
}
