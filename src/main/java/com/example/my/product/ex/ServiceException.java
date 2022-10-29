package com.example.my.product.ex;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.BindException;


import java.util.List;
@Getter
public class ServiceException extends RuntimeException{
    private Integer state;

    public ServiceException(Integer state,String messsage){
        super(messsage);
        this.state = state;
    }




}
