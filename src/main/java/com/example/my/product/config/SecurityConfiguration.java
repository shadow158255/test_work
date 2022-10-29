package com.example.my.product.config;

import com.example.my.product.filter.JwtAuthorizationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtAuthorizationFilter jwtAuthorizationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] urls = {
                "/favicon.ico",
                "/doc.html",
                "/**/*.js",
                "/**/*.css",
                "/swagger-resources/**",
                "/v2/api-docs",
                "/admins/login",
        };
        http.cors(); // 允许跨域访问，关键点在于允许预检请求

        http.csrf().disable();//禁用防止伪造跨域攻击
        //伪造跨域攻击：同一个浏览器，相同平台，打开多个选项卡，在其中一个选项卡登陆后，其他选项卡同时具有登录后相关权限，
        //当其他选项卡没有关闭之关闭当前选项卡时，其他选项卡还具有登陆后相关权限，别人会恶意篡改信息，这就是跨域攻击


        http.authorizeRequests()//要求请求必须被授权
                .antMatchers(urls)//匹配一些路径
                .permitAll()//允许访问
                .anyRequest()//除以上配置以外的请求
                .authenticated();//经过认证的

        //对所有OPTIONS类型的请求（即：所有预检）直接放行：
        //http.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

        //http.formLogin();//启用登陆表单，未授权的请求均会重定向到登陆页面


        //将JWT过滤器添加在认证过滤器之前
        http.addFilterBefore(jwtAuthorizationFilter,UsernamePasswordAuthenticationFilter.class);
    }
}
