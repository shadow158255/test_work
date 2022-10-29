package com.example.my.product.service;

import com.example.my.product.ex.ServiceException;
import com.example.my.product.pojo.dto.AlbumAddNewDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class AlbumServiceTest {

    @Autowired
    IAlbumService service;

    @Test
    void addNew(){
        AlbumAddNewDTO albumAddNewDTO = new AlbumAddNewDTO();
        albumAddNewDTO.setName("牛牛444");
        albumAddNewDTO.setDescription("11");
        albumAddNewDTO.setSort(10);
        try {
            service.addNew(albumAddNewDTO);
            log.debug("添加成功！");
        }catch (ServiceException e){
            log.debug(e.getMessage());
        }
    }

    @Test
    void deleteByIdTest(){
        Long id = 4l;
        service.deleteById(id);
        log.debug("删除完成！");
    }
}
