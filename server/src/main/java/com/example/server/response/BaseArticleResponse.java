package com.example.server.response;

import lombok.Data;

/**
 * @Author: ataobiu
 * @Description: TODO
 * @DateTime: 2023/10/19 23:29
 **/
@Data
public class BaseArticleResponse {
    private Long id;
    private String title;
    private String cover;
    private Long likes;
    private BaseUserResponse user;
}
