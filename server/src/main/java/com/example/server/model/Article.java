package com.example.server.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 用户内容
 * @TableName article
 */
@TableName(value ="article")
@Data
public class Article implements Serializable {
    /**
     * Primary Key
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 内容名称
     */
    private String title;

    /**
     * 内容封面
     */
    private String images;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 是否展示
     */
    private Integer isShow;

    /**
     * 文章标签
     */
    private String tags;

    /**
     * 发布者
     */
    private Long authorId;

    /**
     * 是否删除
     */
    private Boolean deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}