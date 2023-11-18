package com.example.server.request;

import lombok.Data;

/**
 * @Author: ataobiu
 * @Description: TODO
 * @DateTime: 2023/10/20 13:16
 **/
@Data
public class BaseArticleReq {
    private String title;
    private  String content;
}
