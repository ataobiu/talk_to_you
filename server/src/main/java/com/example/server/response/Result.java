package com.example.server.response;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: ataobiu
 * @Description: 响应结果封装
 * @DateTime: 2023/10/15 16:41
 **/
@Data
@Builder
public class Result {
    public static final Integer CODE_SUCCESS = 200;
    public static final Integer CODE_ERROR = 400;
    public static final Integer CODE_AUTH_ERROR = 404;
    public static final Integer CODE_SYS_ERROR = 500;
    private Integer code;
    private String msg;
    private Object data;

    /**
     * 请求成功
     *
     * @return 默认的请求成功
     */
    public static Result success() {
        return new Result(CODE_SUCCESS, "请求成功", null);
    }

    /**
     * 请求成功
     *
     * @param data
     * @return 响应结果
     */
    public static Result success(Object data) {
        return new Result(CODE_SUCCESS, "请求成功", data);
    }

    /**
     * 请求成功
     *
     * @param msg
     * @param data
     * @return 成功信息，响应结果
     */
    public static Result success(String msg, Object data) {
        return new Result(CODE_SUCCESS, msg, data);
    }

    /**
     * 请求失败
     *
     * @return 默认的请求失败
     */
    public static Result error() {
        return new Result(CODE_ERROR, "请求失败", null);
    }

    /**
     * 请求失败
     *
     * @param msg
     * @return 响应错误信息
     */
    public static Result error(String msg) {
        return new Result(CODE_ERROR, msg, null);
    }

    /**
     * 权限认证失败
     *
     * @return 权限认证失败
     */
    public static Result auth_error() {
        return new Result(CODE_AUTH_ERROR, "权限认证失败", null);
    }

    /**
     * 系统错误
     *
     * @return 响应系统错误
     */
    public static Result sys_error() {
        return new Result(CODE_SYS_ERROR, "系统错误", null);
    }

    /**
     * 自定义错误
     *
     * @return
     */
    public static Result sys_error(Integer code, String msg) {
        return new Result(code, msg, null);
    }
}
