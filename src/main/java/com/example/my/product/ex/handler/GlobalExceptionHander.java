package com.example.my.product.ex.handler;

import com.example.my.product.ex.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHander {
    @ExceptionHandler
    public String handleServiceException(ServiceException e){
        log.debug("处理ServiceException：{}",e.getMessage());
        return e.getMessage();
    }

    @ExceptionHandler
    public String handleThrowable(Throwable e){
        log.debug("处理Throwable");
        e.printStackTrace();
        return "程序运行中出先意外错误，请联系系统管理员";
    }

    @ExceptionHandler
    public String handlerBindException(BindException e){
        //Spring Validation检查不通过时，将抛出BindException
        log.debug("处理BindException：{}", e.getMessage());

        StringBuilder stringBuilder = new StringBuilder();
        List<FieldError> fieldErrors = e.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            String message = fieldError.getDefaultMessage();
            stringBuilder.append(message);
        }

        return stringBuilder.toString();
    }
}
