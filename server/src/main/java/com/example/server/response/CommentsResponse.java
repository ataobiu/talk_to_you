package com.example.server.response;

import lombok.Data;

import java.util.List;

/**
 * @Author: ataobiu
 * @Description: TODO
 * @DateTime: 2023/11/8 19:53
 **/
@Data
public class CommentsResponse {
    private Long total;
    private Object comments;
}
