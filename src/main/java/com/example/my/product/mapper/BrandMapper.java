package com.example.my.product.mapper;

import com.example.my.product.pojo.entity.Brand;
import com.example.my.product.pojo.vo.BrandListItemVO;
import com.example.my.product.pojo.vo.BrandStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandMapper {
    int insert(Brand brand);
    int deleteById(Long id);

    int update(Brand brand);


    int count();

    BrandStandardVO getStandardById(Long id);

    List<BrandListItemVO> getList();
}
