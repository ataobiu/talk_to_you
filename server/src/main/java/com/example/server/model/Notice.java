package com.example.server.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 通知栏信息表
 * @TableName notice
 */
@TableName(value ="notice")
@Data
public class Notice implements Serializable {
    /**
     * Primary Key
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 类别
     */
    private String sort;

    /**
     * 内容
     */
    private String content;

    /**
     * 链接
     */
    private String url;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}