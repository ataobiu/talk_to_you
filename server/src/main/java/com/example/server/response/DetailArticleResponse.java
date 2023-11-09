package com.example.server.response;

import lombok.Data;

/**
 * @Author: ataobiu
 * @Description: TODO
 * @DateTime: 2023/11/6 23:11
 **/
@Data
public class DetailArticleResponse extends BaseArticleResponse {
    private String images;
    private String content;
    private Integer isLike = 0;
    private Integer isCollect = 0;
    private Long collects;
    private Object comments;
}
