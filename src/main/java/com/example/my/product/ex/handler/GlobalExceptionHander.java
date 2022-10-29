package com.example.my.product.ex.handler;

import com.example.my.product.ex.ServiceException;
import com.example.my.product.web.JsonResult;
import com.example.my.product.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHander {

    public GlobalExceptionHander(){
        log.debug("创建统一处理异常的类：GlobalExceptionHander");
    }


    @ExceptionHandler
    public JsonResult handleServiceException(ServiceException e){
        log.debug("处理ServiceException：{}",e.getMessage());
        return JsonResult.fail(e);
    }

    @ExceptionHandler
    public JsonResult handleThrowable(Throwable e){
        log.debug("处理Throwable");
        e.printStackTrace();
        String message =  "程序运行中出先意外错误，请联系系统管理员";
        return JsonResult.fail(ServiceCode.ERR_INTERNAL_SERVER_ERROR,message);
    }

    @ExceptionHandler
    public JsonResult handlerBindException(BindException e){
        //Spring Validation检查不通过时，将抛出BindException
        log.debug("处理BindException：{}", e.getMessage());

        StringBuilder stringBuilder = new StringBuilder();
        List<FieldError> fieldErrors = e.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            String message = fieldError.getDefaultMessage();
            stringBuilder.append(message);
        }

        return JsonResult.fail(ServiceCode.ERR_BAD_RESULT,stringBuilder.toString());
    }
}
