package com.example.my.product.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AlbumListItemVO implements Serializable {

    /**
     * 记录id
     */
    private Long id;

    /**
     * 相册名称
     */
    private String name;

    /**
     * 相册简介
     */
    private String description;

    /**
     * 自定义排序序号
     */
    private Integer sort;

}
