package com.example.my.product.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.my.product.pojo.entity.Info;
import com.example.my.product.service.InfoService;

import com.example.my.product.web.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/userInfo")
@RestController
public class InfoController {

    @Autowired
    private InfoService infoService;

    //查询用户列表/条件查询
    @GetMapping("infoList/{page}/{limit}")
    public JsonResult list(@PathVariable Long page,
                           @PathVariable Long limit,
                           Info info) {
        //查询用户列表/条件查询
        IPage<Info> infos = infoService.queryInfoList(page,limit,info);
        return JsonResult.ok(infos);
    }

    //添加用户详情
    @PostMapping("addInfo")
    public JsonResult addInfo(@RequestBody Info info) {
        //添加用户
        boolean isSuccess = infoService.save(info);
        if (isSuccess){
            return JsonResult.ok();
        }else{
            return JsonResult.fail();
        }
    }

    //根据id删除用户详情
    @DeleteMapping("deleteById/{id}")
    public JsonResult deleteById(@PathVariable String id) {
        //
        boolean isSuccess = infoService.removeById(id);
        if (isSuccess){
            return JsonResult.ok();
        }else{
            return JsonResult.fail();
        }
    }

    //根据id修改用户详情
    @PostMapping("updateInfo")
    public JsonResult updateInfo(@RequestBody Info info) {
        boolean isSuccess = infoService.updateById(info);
        if (isSuccess){
            return JsonResult.ok();
        }else{
            return JsonResult.fail();
        }
    }
    //根据id查询用户详情
    @GetMapping("getInfoById/{id}")
    public JsonResult getInfoById(@PathVariable String id) {
        Info getInfoById = infoService.getById(id);
       return JsonResult.ok(getInfoById);
    }

}
