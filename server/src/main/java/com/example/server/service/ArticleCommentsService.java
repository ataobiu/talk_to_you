package com.example.server.service;

import com.example.server.model.ArticleComments;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.request.BaseCommentReq;

/**
 * @author sherry
 * @description 针对表【article_comments(文章评论表)】的数据库操作Service
 * @createDate 2023-10-19 14:33:12
 */
public interface ArticleCommentsService extends IService<ArticleComments> {

    /**
     * 新增评论
     *
     * @param comment
     * @param userId
     */
    boolean addComment(BaseCommentReq comment, Long userId);

    /**
     * 删除评论
     * @param id
     * @param userId
     * @return
     */
    boolean deleteComment(String id, Long userId);
}
