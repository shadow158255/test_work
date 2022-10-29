package com.example.my.product.web;

public interface ServiceCode {
    Integer OK = 20000;

    Integer ERR_BAD_RESULT = 40000;
    Integer ERR_NOT_FOUND =40400;
    Integer ERR_CONFLICT = 40900;

    Integer ERR_INTERNAL_SERVER_ERROR = 50000;
    Integer ERR_INSERT = 50001;
    Integer ERR_DELETE = 50002;
    Integer ERR_UPDATE = 50003;

    /**
     * 请求参数的格式错误
     */
    Integer ERR_BAD_REQUEST = 40000;
    /**
     * 未授权的访问
     */
    Integer ERR_UNAUTHORIZED = 40100;
    /**
     * 未授权的访问：账号禁用
     */
    Integer ERR_UNAUTHORIZED_DISABLED = 40101;
    /**
     * 禁止访问，通常是已登录，但无权限
     */
    Integer ERR_FORBIDDEN = 40300;
    /**
     * 解析JWT失败：格式错误，或签名错误
     */
    Integer ERR_JWT_PARSE = 60000;
    /**
     * 解析JWT失败：过期
     */
    Integer ERR_JWT_EXPIRED = 60001;

}
