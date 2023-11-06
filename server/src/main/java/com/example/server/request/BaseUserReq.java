package com.example.server.request;

import lombok.Data;

/**
 * @Author: ataobiu
 * @Description: 登录用户请求对象
 * @DateTime: 2023/10/19 15:59
 **/
@Data
public class BaseUserReq {
    private String account;
    private String password;
}
