package com.example.my.product.controller;

import com.example.my.product.pojo.dto.AlbumAddNewDTO;
import com.example.my.product.service.IAlbumService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(tags = "04.相册管理模块")
@Slf4j
@RestController
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private IAlbumService albumService;

    public AlbumController(){
        log.info("创建控制器类：AlbumController");
    }


    //http://localhost:9080/albums/add-new?name=XiaoMi
    @ApiOperation("添加相册")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
    public String addNew(@Validated AlbumAddNewDTO albumAddNewDTO){
        log.debug("开始处理【添加相册】的请求：{}",albumAddNewDTO);
        albumService.addNew(albumAddNewDTO);
        return "添加相册成功！";
    }



}
