package com.example.my.product.mapper;

import com.example.my.product.pojo.entity.Picture;
import com.example.my.product.mapper.PictureMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PictureMapperTest {
    @Autowired
    PictureMapper pictureMapper;

    @Test
    void insertTest(){
        Picture picture = new Picture();
        picture.setHeight(200);
        picture.setUrl("dfsdfsdf");
        picture.setWidth(2);
        int rows = pictureMapper.insert(picture);
        System.out.println("rows" + rows);

    }

    @Test
    void deleteById(){
        Long id = 3l;
        int rows =pictureMapper.deleteById(id);
        System.out.println("rows"+rows);

    }

    @Test
    void deleteByIds(){
        List list = new ArrayList();
        list.add(5l);
        list.add(4l);
        int rows = pictureMapper.deleteByIds(list);
        System.out.println("rows" + rows);
    }

}
