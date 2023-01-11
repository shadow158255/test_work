package com.example.my.product.web;

public interface ServiceCode {
    /**
     * 成功
     */
    Integer OK = 20000;
    Integer ERR_BAD_RESULT = 40000;
    Integer ERR_NOT_FOUND =40400;
    Integer ERR_CONFLICT = 40900;

    Integer ERR_INTERNAL_SERVER_ERROR = 50000;
    Integer ERR_INSERT = 50001;
    Integer ERR_DELETE = 50002;
    Integer ERR_UPDATE = 50003;

    /**
     * 账号密码错误
     */
    Integer ERR_ACCOUNT_PASSWORD = 50000;
    /**
     * 用户名不存在
     */
    Integer ERR_ACCOUNT_NOTFOUND = 50100;
    /**
     * 用户名已存在
     */
    Integer ERR_ACCOUNT_EXIST = 50200;
    /**
     * 用户名或密码不能为空
     */
    Integer ERR_ACCOUNT_PASSWORD_NOT_NULL = 50300;
    /**
     * 账号冻结
     */
    Integer ERR_FREEZE_AN_ACCOUNT = 50400;

}
