package com.example.my.product.web;

import lombok.Data;

import java.io.Serializable;

@Data
public class JsonResult implements Serializable {
    private Integer state;
    private String message;
    private Object data;
}
