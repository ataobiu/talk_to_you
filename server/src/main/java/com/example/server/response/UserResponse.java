package com.example.server.response;

import lombok.Data;

/**
 * @Author: ataobiu
 * @Description: TODO
 * @DateTime: 2023/10/19 15:25
 **/
@Data
public class UserResponse extends BaseUserResponse{
    private String account;
    private String email;
    private String intro;
    private Integer role;

}
