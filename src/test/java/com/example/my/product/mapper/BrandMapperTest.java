package com.example.my.product.mapper;

import com.example.my.product.pojo.entity.Brand;
import com.example.my.product.mapper.BrandMapper;
import com.example.my.product.pojo.vo.BrandListItemVO;
import com.example.my.product.pojo.vo.BrandStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class BrandMapperTest {
    @Autowired
    BrandMapper brandMapper;

    @Test
    void insertTest(){
        Brand brand = new Brand();
        brand.setName("盖亚");
        brand.setDescription("我是盖亚！");
        brand.setSort(1);
        int rows = brandMapper.insert(brand);
        System.out.println("受影响的行数rows = " + rows);


    }

    @Test
    void deleteBrandTest(){
        Long id = 5l;
        int rows = brandMapper.deleteById(id);
        System.out.println("rows = " + rows);
    }

    @Test
    void updateByName(){
        Long id = 4l;
        String name = "小米222";
        String des= "小米111 ";
        Integer sort = 56;
        Brand brand = new Brand();
        brand.setId(id);
        brand.setName(name);
        brand.setDescription(des);
        brand.setSort(sort);
        int rows = brandMapper.update(brand);
        System.out.println("rows " + rows);


    }

    @Test
    void countTest(){
        int rows = brandMapper.count();
        System.out.println(rows);
    }

    @Test
    void getStandartByIdTest(){
        Long id = 2l;
        BrandStandardVO brandStandardVO = brandMapper.getStandardById(id);
        log.info("通过id={}查询完成，查询结果brand={}",id,brandStandardVO);
    }

    @Test
    void getListTest(){
        List<BrandListItemVO> list = brandMapper.getList();
        log.info("查询完成，查询到{}条数据",list.size());
        for (BrandListItemVO item:list) {
            log.info("{}",item);
        }
    }
}
