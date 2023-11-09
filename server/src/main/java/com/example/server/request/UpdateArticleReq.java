package com.example.server.request;

import lombok.Data;

/**
 * @Author: ataobiu
 * @Description: TODO
 * @DateTime: 2023/10/20 0:21
 **/
@Data
public class UpdateArticleReq extends BaseArticleReq{
    private Long id;
    private String title;
    private String content;
    private Integer isShow;
    private String tags;
}
