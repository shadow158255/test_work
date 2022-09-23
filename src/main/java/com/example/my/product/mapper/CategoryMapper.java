package com.example.my.product.mapper;

import com.example.my.product.pojo.entity.Category;
import com.example.my.product.pojo.vo.CategoryListItemVO;
import com.example.my.product.pojo.vo.CategoryStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {
    int insert(Category category);

    int deleteById(Long id);

    int update(Category category);

    CategoryStandardVO getStandardById(Long id);

    List<CategoryListItemVO> getList();
}
