package com.example.server.request;

import lombok.Data;

/**
 * @Author: ataobiu
 * @Description: 注册用户请求对象
 * @DateTime: 2023/10/19 16:08
 **/
@Data
public class RegisterUserReq extends BaseUserReq {
    private String email;
}
