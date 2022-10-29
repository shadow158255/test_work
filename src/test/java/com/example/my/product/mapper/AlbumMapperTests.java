package com.example.my.product.mapper;

import com.example.my.product.pojo.entity.Album;
import com.example.my.product.pojo.vo.AlbumListItemVO;
import com.example.my.product.pojo.vo.AlbumStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class AlbumMapperTests {

    @Autowired
    AlbumMapper albumMapper;

    @Test
    void testInsert(){
        Album album = new Album();
        album.setName("122x");
        album.setDescription("ganjinxuexi");
        album.setSort(45);
        int rows = albumMapper.insert(album);
        System.out.println("受影响的行数rows = " + rows);

    }



    @Test
    void testInsertBatch(){
        List<Album> albums = new ArrayList<>();
        albums.add(new Album("批量插入测试","fsfsdf",45));
        albums.add(new Album("批量插入测试","fdsafdsa",23));
        int rows = albumMapper.insertBatch(albums);
        System.out.println("rows= " + rows);


    }

    @Test
    void deleteByIdTest(){
        Long id = 1l;
        int rows = albumMapper.deleteById(id);
        System.out.println("rows" + rows);
    }

    @Test
    void deleteByIdsTest(){
        Long[] id = {1l,5l,3l};
            int rows = albumMapper.deleteByIds(id);
            System.out.println(rows);



    }


    @Test
    void updateByIdTest(){
        Long id = 2l;
        String name = "23";
        Integer sort = 25;
         Album album = new Album();
         album.setId(id);
         album.setName(name);
         album.setSort(sort);

         int rows = albumMapper.updateById(album);
        System.out.println("rows " + rows);


    }

    @Test
    void countTest(){
        int count = albumMapper.count();
        System.out.println(count);
    }

    @Test
    void countByNameTest(){
        String name = "xzx";
        int count = albumMapper.countByName(name);
        log.info("根据名字【{}】查询完成，查询结果为{}个",name,count);
    }


    @Test
    void getStandardByIdTest(){
        Long id = 4l;
        AlbumStandardVO album= albumMapper.getStandardById(id);
        log.info("根据id={}查询完成，查询结果={}", id, album);
    }

    @Test
    void getListItemTest(){
        List<AlbumListItemVO> list = albumMapper.getList();
        log.info("查询列表完成，结果集中的数量={}" ,list.size());
        for (AlbumListItemVO album :
                list) {
            log.info("{}",album);
        }
    }




}
