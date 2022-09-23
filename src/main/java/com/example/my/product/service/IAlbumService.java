package com.example.my.product.service;

import com.example.my.product.pojo.dto.AlbumAddNewDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IAlbumService {

    void addNew(AlbumAddNewDTO albumAddNewDTO);
}
