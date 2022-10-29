package com.example.my.product.controller;

import com.example.my.product.pojo.dto.AlbumAddNewDTO;
import com.example.my.product.security.LoginPrincipal;
import com.example.my.product.service.IAlbumService;
import com.example.my.product.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public JsonResult addNew(@Validated AlbumAddNewDTO albumAddNewDTO,
    @AuthenticationPrincipal LoginPrincipal loginPrincipal){
        log.debug("开始处理【添加相册】的请求：{}",albumAddNewDTO);
        log.debug("当前登录的用户（当事人）的id为：{}",loginPrincipal.getId());
        log.debug("当前登录的用户（当事人）的username为：{}",loginPrincipal.getUsername());
        albumService.addNew(albumAddNewDTO);
        return JsonResult.ok();
    }


    //http://localhost:9080/albums/delete
    @ApiOperation("删除相册")
    @ApiOperationSupport(order = 200)
    @PostMapping("/delete/{id}")
    public  JsonResult deleteById(@PathVariable Long id){
        log.debug("开始处理【删除相册】的请求：id={}",id);
        System.out.println("git test!");
        albumService.deleteById(id);
        return  JsonResult.ok();
    }



}
