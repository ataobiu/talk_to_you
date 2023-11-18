package com.example.server.request;

import lombok.Data;

/**
 * @Author: ataobiu
 * @Description: TODO
 * @DateTime: 2023/11/9 12:51
 **/
@Data
public class BaseCommentReq {
    private Long id;
    private String comment;
}
