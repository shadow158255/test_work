package com.example.my.product.controller;



import com.example.my.product.ex.ServiceException;
import com.example.my.product.pojo.entity.User;
import com.example.my.product.service.UserService;
import com.example.my.product.utils.MD5;
import com.example.my.product.web.JsonResult;
import com.example.my.product.web.ServiceCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/index")
@RestController
public class IndexController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public JsonResult login(@RequestBody User user){
        userService.login(user);
        Map<String,Object> map = new HashMap<>();
        map.put("token","zx");
        return JsonResult.ok(map);
    }
    @GetMapping("info")
    public JsonResult info(){
        Map<String,Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("name","admin zx");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return JsonResult.ok(map);
    }
    @PostMapping("reg")
    public JsonResult reg(@RequestBody User user){
        userService.reg(user);
        return JsonResult.ok();
    }


    @PostMapping("/logout")
    public JsonResult logout(){
        return JsonResult.ok();
    }

    
}
