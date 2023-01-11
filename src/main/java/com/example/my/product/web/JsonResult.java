package com.example.my.product.web;

import com.example.my.product.ex.ServiceException;
import lombok.Data;

import java.io.Serializable;

@Data
public class JsonResult<T> implements Serializable {
    private Integer state;
    private String message;
    private T data;

    public static JsonResult ok(){
        return ok(null);
    }
    public static <T> JsonResult<T> ok(T data) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setState(ServiceCode.OK);
        jsonResult.setData(data);
        return jsonResult;
    }

    public static JsonResult<Void> fail(ServiceException e){
        return fail(e.getState(), e.getMessage());
    }

    public static JsonResult<Void> fail(Integer state,String message){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setState(state);
        jsonResult.setMessage(message);
        return jsonResult;
    }
    public static JsonResult<Void> fail(){
        return JsonResult.fail(null);
    }
}
