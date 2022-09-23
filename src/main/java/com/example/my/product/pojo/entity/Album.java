package com.example.my.product.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Album implements Serializable {
    private Long id;

    private String name;

    private String description;

    private Integer sort;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;


    public Album(String name,String description,Integer sort){
        this.name = name;
        this.description = description;
        this.sort = sort;
    }
}
