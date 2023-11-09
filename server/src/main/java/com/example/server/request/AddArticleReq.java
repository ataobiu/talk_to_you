package com.example.server.request;

import lombok.Data;

/**
 * @Author: ataobiu
 * @Description: TODO
 * @DateTime: 2023/11/10 2:37
 **/
@Data
public class AddArticleReq extends BaseArticleReq {
    private String images;
    private Integer isShow;

}
