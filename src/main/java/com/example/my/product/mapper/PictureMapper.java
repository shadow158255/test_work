package com.example.my.product.mapper;

import com.example.my.product.pojo.entity.Picture;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureMapper {
    int insert(Picture picture);

    int deleteById(Long id);

    int deleteByIds(List<Long> ids);

}
