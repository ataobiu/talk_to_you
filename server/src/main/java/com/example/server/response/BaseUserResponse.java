package com.example.server.response;

import lombok.Data;

/**
 * @Author: ataobiu
 * @Description: TODO
 * @DateTime: 2023/11/3 20:39
 **/
@Data
public class BaseUserResponse {
    private Long id;
    private String name;
    private String avatar;
}
