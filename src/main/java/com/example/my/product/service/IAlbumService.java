package com.example.my.product.service;

import com.example.my.product.pojo.dto.AlbumAddNewDTO;
import com.example.my.product.web.ServiceCode;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IAlbumService extends ServiceCode {

    void addNew(AlbumAddNewDTO albumAddNewDTO);

    void deleteById(Long id);
}
