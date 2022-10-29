package com.example.my.product.service.impl;

import com.example.my.product.ex.ServiceException;
import com.example.my.product.mapper.AlbumMapper;
import com.example.my.product.pojo.dto.AlbumAddNewDTO;
import com.example.my.product.pojo.entity.Album;
import com.example.my.product.service.IAlbumService;
import com.example.my.product.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class AlbumServiceImpl implements IAlbumService, ServiceCode {

    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public void addNew(AlbumAddNewDTO albumAddNewDTO) {
        log.debug("开始处理【添加相册】的业务，参数：{}", albumAddNewDTO);
        // 调用AlbumMapper对象的int countByName(String name)方法统计此名称的相册的数量
        String name = albumAddNewDTO.getName();
        int countByName = albumMapper.countByName(name);
        log.debug("尝试添加的相册名称是：{}，在数据库中此名称的相册数量为：{}", name, countByName);
        // 判断统计结果是否大于0
        if (countByName > 0) {
            // 是：相册名称已经存在，抛出RuntimeException异常
            String message = "添加相册失败！相册名称【" + name + "】已存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        // 获取当前时间：LocalDateTime now = LocalDateTime.now()
        LocalDateTime now = LocalDateTime.now();
        // 创建Album对象
        Album album = new Album();
        // 补全Album对象中各属性的值：name：来自参数
        // 补全Album对象中各属性的值：description：来自参数
        // 补全Album对象中各属性的值：sort：来自参数
        BeanUtils.copyProperties(albumAddNewDTO, album);
        // 补全Album对象中各属性的值：gmtCreate：now
        album.setGmtCreate(now);
        // 补全Album对象中各属性的值：gmtModified：now
        album.setGmtModified(now);
        // 调用AlbumMapper对象的int insert(Album album)方法插入相册数据
        log.debug("即将向数据库中插入数据：{}", album);
        int rows = albumMapper.insert(album);
        // 判断插入数据时受影响的行数是否有误
        if (rows != 1) {
            String message = "添加相册失败！服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }
    }

    @Override
    public void deleteById(Long id) {
        log.debug("开始处理【添加相册】的请求，参数{}",id);
        //检查尝试删除的相册是否存在
        Object queryResult = albumMapper.getStandardById(id);
        if (queryResult == null){
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,"删除相册失败");
        }

        //执行删除
        log.debug("即使删除id为{}的相册。。",id);
        int rows = albumMapper.deleteById(id);
        if (rows != 1){
            throw new ServiceException(ServiceCode.ERR_DELETE,"删除相册失败");
        }
        log.debug("删除完成");
    }
}
