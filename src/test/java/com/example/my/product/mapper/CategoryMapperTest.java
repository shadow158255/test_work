package com.example.my.product.mapper;

import com.example.my.product.pojo.entity.Category;
import com.example.my.product.mapper.CategoryMapper;
import com.example.my.product.pojo.vo.CategoryListItemVO;
import com.example.my.product.pojo.vo.CategoryStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class CategoryMapperTest {
    @Autowired
    CategoryMapper categoryMapper;

    @Test
    void insert(){
        Category category = new Category();
        category.setEnable(1);
        category.setKeywords("zhagnsan");
        int rows = categoryMapper.insert(category);
        System.out.println("rows=" + rows);
    }

    @Test
    void deleteByIdTest(){
        Long id = 8l;
        int rows = categoryMapper.deleteById(id);
        System.out.println("rows = " + rows);

    }


    @Test
    void updateTest(){
        String name = "电脑整机";
        String keywords = "放大发";
        Integer sort = 4;
        Category category = new Category();
        category.setKeywords(keywords);
        category.setName(name);
        category.setSort(sort);
        int rows = categoryMapper.update(category);
        System.out.println("rows" + rows);
    }

    @Test
    void getStandardByIdTest(){
        Long id = 3l;
        CategoryStandardVO categoryStandardVO = categoryMapper.getStandardById(id);
        log.info("通过id={}查询成功，查询结果为cate={}" ,id,categoryStandardVO);
    }

    @Test
    void getListTest(){
        List<CategoryListItemVO> list = categoryMapper.getList();
        log.info("查询完成，查询到{}个结果",list.size());
        for (CategoryListItemVO item:
             list) {
            log.info("{}",item);
        }
    }


}
