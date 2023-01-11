package com.example.my.product.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.my.product.pojo.base.BaseEntity;
import lombok.Data;

@Data
@TableName("info")
public class Info extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @TableField("name")
    private String name;
    @TableField("address")
    private String address;
    @TableField("city")
    private String city;
    @TableField("postcode")
    private String postcode;
    @TableField("license_plate")
    private String licensePlate;
    @TableField("color")
    private String color;
    @TableField("company_suffix")
    private String companySuffix;
    @TableField("email")
    private String email;
    @TableField("job")
    private String job;
    @TableField("phone_number")
    private String phoneNumber;
    @TableField("ip")
    private String ip;

}
