package com.example.my.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.my.product.pojo.entity.Info;
import java.util.List;

public interface InfoService extends IService<Info> {
    IPage<Info> queryInfoList(Long page, Long limit, Info info);
}
