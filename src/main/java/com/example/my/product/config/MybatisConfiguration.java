package com.example.my.product.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.my.product.mapper")
public class MybatisConfiguration {

}
