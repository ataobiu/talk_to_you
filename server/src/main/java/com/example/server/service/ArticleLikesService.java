package com.example.server.service;

import com.example.server.model.ArticleLikes;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author sherry
 * @description 针对表【article_likes(文章点赞表)】的数据库操作Service
 * @createDate 2023-10-19 14:33:08
 */
public interface ArticleLikesService extends IService<ArticleLikes> {

    /**
     * 点赞或取消点赞
     *
     * @param id
     * @param userId
     * @return
     */
    boolean addOrDeleteLike(String id, Long userId);
}
