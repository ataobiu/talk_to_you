package com.example.server.service;

import com.example.server.model.Article;
import com.example.server.model.ArticleComments;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.model.User;
import com.example.server.response.BaseArticleResponse;
import com.example.server.response.DetailArticleResponse;

import java.util.List;

/**
 * @author sherry
 * @description 针对表【article(用户内容)】的数据库操作Service
 * @createDate 2023-10-19 14:33:15
 */
public interface ArticleService extends IService<Article> {
    /**
     * 获取公开的文章内容
     *
     * @return
     */
    List<BaseArticleResponse> getExploreArticle();

    /**
     * 通过用户id获取私有的文章内容
     *
     * @param id 用户id
     * @return
     */
    List<BaseArticleResponse> getPrivateArticleById(Long userId);

    /**
     * 根据文章id，用户id，删除个人文章
     *
     * @param id     文章id
     * @param userId 用户id
     */
    boolean deleteArticleById(Long id, Long userId);

    /**
     * 修改个人文章内容
     *
     * @param article 要修改的文章内容
     * @param userId  用户id
     * @return
     */
    boolean updateArticleById(Article article, Long userId);

    /**
     * 获取个人点赞的文章内容
     *
     * @param userId 用户id
     * @return 点赞的文章列表
     */
    List<BaseArticleResponse> getLikeArticleById(Long userId);

    /**
     * 获取个人收藏的文章内容
     *
     * @param userId
     * @return
     */
    List<BaseArticleResponse> getCollectdArticleById(Long userId);

    /**
     * 获取作者信息
     *
     * @param authorId
     * @return
     */
    User getArticleAuthorInfoById(Long authorId);

    /**
     * 获取文章点赞量
     *
     * @param articleId
     * @return
     */
    Long getArticleLikesCountById(Long articleId);

    /**
     * 获取文章收藏数量
     *
     * @param articleId
     * @return
     */
    Long getArticleCollectsCountById(Long articleId);

    /**
     * 获取文章评论数量
     *
     * @param articleId
     * @return
     */
    Long getArticleCommentsCountById(Long articleId);

    /**
     * 根据文章id获取文章详细内容
     *
     * @param articleId
     * @return
     */
    DetailArticleResponse getDetailByArticleId(Long articleId);

    /**
     * 根据文章id,作者id获取文章详细内容（包含私人文章）
     *
     * @param articleId
     * @param userId
     * @return
     */
    DetailArticleResponse getDetailByArticleId(Long articleId, Long userId);

    /**
     * 根据文章id查询文章对应评论
     *
     * @param articleId
     * @return
     */
    List<ArticleComments> getArticleCommentsById(Long articleId);


}
