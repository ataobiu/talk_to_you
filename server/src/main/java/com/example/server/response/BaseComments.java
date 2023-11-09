package com.example.server.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: ataobiu
 * @Description: TODO
 * @DateTime: 2023/11/8 19:34
 **/
@Data
public class BaseComments {
    private Long id;
    private String name;
    private String avatar;
    private String comment;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;
    private Long commentId;
}