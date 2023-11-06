package com.example.server.request;

import lombok.Data;

/**
 * @Author: ataobiu
 * @Description: 更新用户请求对象
 * @DateTime: 2023/10/19 16:14
 **/
@Data
public class UpdateUserReqReq extends RegisterUserReq {
    private String name;
    private String avatar;
    private String intro;

}
