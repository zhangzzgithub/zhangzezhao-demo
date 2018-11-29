package com.primeton.zhangzezhao.demo.exception;

/**
 * 异常枚举类
 */
public enum ErrorEnum {

	SYSTEM_ERROR("00000","系统错误"),
    ILLEGAL_USERNAME("00001","用户名格式错误"),
    ILLEGAL_PASSWORD("00002","密码格式错误"),
    ERROR_PASSWORD("00003","用户密码错误"),
    USER_NOT_FOUND("00004","用户不存在"),
    USERNAME_CONFLICT("00005","用户名冲突"),
    ORGNAME_CONFLICT("00006","部门名冲突"),
    ORG_NOT_FOUND("00007","组织机构不存在"),
    ERROR_DELETE_ORG("00008","该组织有用户或下级组织"), 
    ERROR_UPDATE_ORG("00009","更新出现错误");

    private String code;
    private String msg;

    ErrorEnum(String code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    public String getCode()
    {
        return code;
    }

    public String getMsg()
    {
        return msg;
    }
}
