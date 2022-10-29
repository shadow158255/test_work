package com.example.my.product.filter;

import com.alibaba.fastjson.JSON;

import com.example.my.product.security.LoginPrincipal;
import com.example.my.product.web.JsonResult;
import com.example.my.product.web.ServiceCode;
import io.jsonwebtoken.*;
import jdk.nashorn.api.scripting.ScriptUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Value("${csmall.jwt.secret-key}")
    private String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("执行了JwtAuthorizationFilter");

        //清除Security上下文中的数据,每次一开始做一次清除，否则下次请求不携带JWT也会认识
        SecurityContextHolder.clearContext();

        //从请求头中获取到JWT
        String jwt = request.getHeader("Authorization");
        log.debug("从请求头中获取JWT：{}",jwt);

        //判断数据是否不存在
        if (!StringUtils.hasText(jwt) || jwt.length() < 80){
            log.debug("获取到的JWT是无效的，直接放行，交由后续的组件继续执行");
            filterChain.doFilter(request,response);
            //返回终止方法
            return;
        }
        log.debug("尝试解析JWT。。。");
        //尝试解析JWT
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
        }catch (MalformedJwtException e){
            log.warn("解析JWT失败：{}：{}", e.getClass().getName(), e.getMessage());
            JsonResult<Void> jsonResult = JsonResult.fail(
                    ServiceCode.ERR_JWT_PARSE, "无法获取到有效的登录信息，请重新登录！");
            String jsonResultString = JSON.toJSONString(jsonResult);
            PrintWriter writer = response.getWriter();
            writer.println(jsonResultString);
            writer.close();
            return;
        }catch (ExpiredJwtException e){
            log.warn("解析JWT失败：{}：{}", e.getClass().getName(), e.getMessage());
            JsonResult<Void> jsonResult = JsonResult.fail(
                    ServiceCode.ERR_JWT_EXPIRED, "登录信息已过期，请重新登录！");
            String jsonResultString = JSON.toJSONString(jsonResult);
            PrintWriter writer = response.getWriter();
            writer.println(jsonResultString);
            writer.close();
            return;
        }catch (SignatureException e){
            log.warn("解析JWT失败：{}：{}", e.getClass().getName(), e.getMessage());
            JsonResult<Void> jsonResult = JsonResult.fail(
                    ServiceCode.ERR_JWT_PARSE, "无法获取到有效的登录信息，请重新登录！");
            String jsonResultString = JSON.toJSONString(jsonResult);
            PrintWriter writer = response.getWriter();
            writer.println(jsonResultString);
            writer.close();
            return;
        }catch (Throwable e){
            log.warn("解析JWT失败：{}：{}",e.getClass().getName(),e.getMessage());
            JsonResult<Void> jsonResult = JsonResult.fail(ServiceCode.ERR_JWT_PARSE,"无法获取到登录有效信息，请尝试重新登录！");
            String jsonResultString = JSON.toJSONString(jsonResult);
            PrintWriter writer = response.getWriter();
            writer.println(jsonResultString);
            writer.close();
            return;
        }

        //从JWT的解析结果中获取数据
        Long id = claims.get("id",Long.class);
        String username = claims.get("username",String.class);
        Object authorityListString = claims.get("authorities");
        log.debug("从JWT中解析到id为：{}",id);
        log.debug("从JWT中解析到username为：{}",username);
        log.debug("从JWT中解析到authorityListString为：{}",authorityListString);

        //准备Authentization对象，后续会将此对象封装到Security的上下文中
        LoginPrincipal loginPrincipal = new LoginPrincipal();//将username和id封装到这个对象中，作为参数传入Spring Security上下文中
        loginPrincipal.setId(id);
        loginPrincipal.setUsername(username);

       /* String listTxt = JSON.toJSONString(authorityListString);
        List<SimpleGrantedAuthority>  authorities = JSON.parseArray(
                listTxt,SimpleGrantedAuthority.class);//(字符串格式，加转换的类型）
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginPrincipal,null,authorities);*/

        List<SimpleGrantedAuthority> authorities = JSON.parseArray(
                (String) authorityListString, SimpleGrantedAuthority.class);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginPrincipal, null, authorities);
        //旧JWT：eyJhbGciOiJIUzI1NiIsInR5cCI6Imp3dCJ9.eyJleHAiOjE2NjU0MDY4NTUsInVzZXJuYW1lIjoicm9vdCJ9.3D6ey7_EwbiM-vuARqd0UMgdActzzFZxXlz4gylkMx0
        //新JWT：eyJhbGciOiJIUzI1NiIsInR5cCI6Imp3dCJ9.eyJleHAiOjE2NjU0NTUyODUsImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiIvYW1zL2FkbWluL2RlbGV0ZSJ9LHsiYXV0aG9yaXR5IjoiL2Ftcy9hZG1pbi9yZWFkIn0seyJhdXRob3JpdHkiOiIvYW1zL2FkbWluL3VwZGF0ZSJ9LHsiYXV0aG9yaXR5IjoiL3Btcy9wcm9kdWN0L2RlbGV0ZSJ9LHsiYXV0aG9yaXR5IjoiL3Btcy9wcm9kdWN0L3JlYWQifSx7ImF1dGhvcml0eSI6Ii9wbXMvcHJvZHVjdC91cGRhdGUifV0sInVzZXJuYW1lIjoicm9vdCJ9.iSPQvV6lr43qhnKuZMjMiO7joGknCu9YoQnV28CeiU4


        //将用户信息封装到Security的上下文中
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        log.debug("已经向Security的上下文中写入：{}",authentication);
        //过滤器继续执行，相当于：放行
        filterChain.doFilter(request,response);
    }
}
