package com.example.my.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.my.product.mapper.InfoMapper;
import com.example.my.product.pojo.entity.Info;
import com.example.my.product.service.InfoService;
import com.example.my.product.web.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;


@Service
public class InfoServiceImpl extends ServiceImpl<InfoMapper, Info> implements InfoService {

    @Autowired
    private InfoMapper infoMapper;

    @Override
    public IPage<Info> queryInfoList(Long page, Long limit, Info info) {
        Page<Info> infoPage = new Page<>(page,limit);
        //封装条件
        //根据姓名和手机号筛选
        QueryWrapper<Info> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(info.getName())) {
            wrapper.like("name",info.getName());
        }
        if(!StringUtils.isEmpty(info.getPhoneNumber())) {
            wrapper.ge("phone_number",info.getPhoneNumber());
        }
        //调用mapper分页方法
        return infoMapper.selectPage(infoPage,wrapper);
    }
}
