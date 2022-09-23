package com.example.my.product.mapper;

import com.example.my.product.pojo.entity.Album;
import com.example.my.product.pojo.vo.AlbumListItemVO;
import com.example.my.product.pojo.vo.AlbumStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumMapper {
    int insert(Album album);

    int insertBatch(List<Album> albums);

    int deleteById(Long id);

    int deleteByIds(Long... ids);

    int updateById(Album album);

    int count();

    int countByName(String name);

    AlbumStandardVO getStandardById(Long id);

    List<AlbumListItemVO> getList();


}
