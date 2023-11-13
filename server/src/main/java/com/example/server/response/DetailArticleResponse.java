package com.example.server.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author: ataobiu
 * @Description: TODO
 * @DateTime: 2023/11/6 23:11
 **/
@Data
public class DetailArticleResponse extends BaseArticleResponse {
    private String images;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;
    private Integer isLike = 0;
    private Integer isCollect = 0;
    private Long collects;
    private Object comments;
}
