package com.example.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.example.server.exception.ServiceException;
import com.example.server.mapper.ArticleMapper;
import com.example.server.model.*;
import com.example.server.request.AddArticleReq;
import com.example.server.request.UpdateArticleReq;
import com.example.server.response.*;
import com.example.server.service.ArticleService;
import lombok.Data;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author sherry
 * @description 针对表【article(用户内容)】的数据库操作Service实现
 * @createDate 2023-10-19 14:33:15
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    // 用户公开的文章 0 公开 1 私密
    private static final Integer SHOW = 0;

    @Override
    public List<BaseArticleResponse> getExploreArticle() {
        List<Article> list = lambdaQuery().eq(Article::getIsShow, SHOW).list();
        return getArticleResponse(list);
    }

    @Override
    public List<BaseArticleResponse> getPrivateArticleById(Long userId) {
        List<Article> list = lambdaQuery().eq(Article::getAuthorId, userId).list();
        return getArticleResponse(list);
    }

    @Override
    public boolean deleteArticleById(Long id, Long userId) {
        return lambdaUpdate().eq(Article::getId, id).eq(Article::getAuthorId, userId).remove();
    }

    @Override
    public boolean updateArticleById(Article article, Long userId) {
        return lambdaUpdate().eq(Article::getAuthorId, userId).set(article.getTitle() != null, Article::getTitle, article.getTitle()).set(article.getContent() != null, Article::getContent, article.getContent()).set(article.getIsShow() != null, Article::getIsShow, article.getIsShow()).set(article.getTags() != null, Article::getTags, article.getTags()).update();
    }

    @Override
    public List<BaseArticleResponse> getLikeArticleById(Long userId) {
        // 查询用户点赞的文章id列表
        List<ArticleLikes> likesList = Db.lambdaQuery(ArticleLikes.class).select(ArticleLikes::getArticleId).eq(ArticleLikes::getUserId, userId).list();
        List articleIdList = new ArrayList<>();
        for (ArticleLikes likes : likesList) {
            articleIdList.add(likes.getArticleId());
        }
        // 查询文章
        List<Article> list = lambdaQuery().in(Article::getId, articleIdList).eq(Article::getIsShow, SHOW).list();
        return getArticleResponse(list);
    }

    @Override
    public List<BaseArticleResponse> getCollectdArticleById(Long userId) {
        // 查询用户收藏的文章id列表
        List<ArticleCollects> likesList = Db.lambdaQuery(ArticleCollects.class).select(ArticleCollects::getArticleId).eq(ArticleCollects::getUserId, userId).list();
        List articleIdList = new ArrayList<>();
        for (ArticleCollects collects : likesList) {
            articleIdList.add(collects.getArticleId());
        }
        // 查询文章
        List<Article> list = lambdaQuery().in(Article::getId, articleIdList).eq(Article::getIsShow, SHOW).list();
        return getArticleResponse(list);
    }

    @Override
    public User getArticleAuthorInfoById(Long authorId) {
        return Db.lambdaQuery(User.class).select(User::getId, User::getName, User::getAvatar).eq(User::getId, authorId).one();
    }

    @Override
    public Long getArticleLikesCountById(Long articleId) {
        return Db.lambdaQuery(ArticleLikes.class).eq(ArticleLikes::getArticleId, articleId).count();
    }

    @Override
    public Long getArticleCollectsCountById(Long articleId) {
        return Db.lambdaQuery(ArticleCollects.class).eq(ArticleCollects::getArticleId, articleId).count();
    }

    @Override
    public DetailArticleResponse getDetailByArticleId(Long articleId) {
        try {
            Article one = lambdaQuery().eq(Article::getId, articleId).eq(Article::getIsShow, SHOW).one();
            return getArticleResponse(one, null);
        } catch (Exception e) {
            throw new ServiceException("获取文章详情失败");
        }
    }

    @Override
    public DetailArticleResponse getDetailByArticleId(Long articleId, Long userId) {
        try {
            Article one = lambdaQuery().eq(Article::getId, articleId).one();
            if (Objects.equals(one.getIsShow(), SHOW) || one.getAuthorId().equals(userId)) {
                return getArticleResponse(one, userId);
            }
            return null;
        } catch (Exception e) {
            throw new ServiceException("获取文章详情失败");
        }
    }

    @Override
    public List<ArticleComments> getArticleCommentsById(Long articleId) {
        return Db.lambdaQuery(ArticleComments.class).eq(ArticleComments::getArticleId, articleId).orderByDesc(ArticleComments::getCreateTime).list();
    }

    @Override
    public boolean isLike(Long articleId, Long userId) {
        val one = Db.lambdaQuery(ArticleLikes.class).eq(ArticleLikes::getArticleId, articleId).eq(ArticleLikes::getUserId, userId).one();
        return one != null;
    }

    @Override
    public boolean isCollect(Long articleId, Long userId) {
        val one = Db.lambdaQuery(ArticleCollects.class).eq(ArticleCollects::getArticleId, articleId).eq(ArticleCollects::getUserId, userId).one();
        return one != null;
    }

    @Override
    public boolean addArticle(Long userId, AddArticleReq addArticleReq) {
        val article = new Article();
        article.setAuthorId(userId);
        article.setTitle(addArticleReq.getTitle());
        article.setContent(addArticleReq.getContent());
        article.setImages(addArticleReq.getImages());
        article.setIsShow(addArticleReq.getIsShow());
        return save(article);
    }

    @Override
    public List<BaseArticleResponse> searchArticle(String title) {
        val list = lambdaQuery().like(Article::getTitle, title).list();
        return getArticleResponse(list);
    }

    @Override
    public Long getArticleCommentsCountById(Long articleId) {
        return Db.lambdaQuery(ArticleComments.class).eq(ArticleComments::getArticleId, articleId).count();
    }

    /**
     * 封装文章响应结构
     *
     * @param articles
     * @return
     */
    public List<BaseArticleResponse> getArticleResponse(List<Article> articles) {
        List<BaseArticleResponse> responseList = new ArrayList<>();
        for (Article article : articles) {

            BaseArticleResponse baseArticleResponse = new BaseArticleResponse();
            BeanUtils.copyProperties(article, baseArticleResponse);
            baseArticleResponse.setCover(article.getImages().split(",")[0]);

            // 根据文章id查询点赞量
            Long likes = getArticleLikesCountById(article.getId());
            baseArticleResponse.setLikes(likes);

            // 根据文章作者id查询作者信息
            User articleAuthorInfo = getArticleAuthorInfoById(article.getAuthorId());
            BaseUserResponse baseUserResponse = new BaseUserResponse();
            BeanUtils.copyProperties(articleAuthorInfo, baseUserResponse);
            baseArticleResponse.setUser(baseUserResponse);
            responseList.add(baseArticleResponse);
        }
        return responseList;
    }

    /**
     * 文章预览详细响应结构封装
     *
     * @param article
     * @return
     */
    public DetailArticleResponse getArticleResponse(Article article, Long userId) {

        DetailArticleResponse detailArticleResponse = new DetailArticleResponse();
        BeanUtils.copyProperties(article, detailArticleResponse);

        // 根据文章id查询点赞量
        Long likesCount = getArticleLikesCountById(article.getId());
        detailArticleResponse.setLikes(likesCount);

        // 评论数量
        Long commentsCount = getArticleCommentsCountById(article.getId());
        CommentsResponse commentsResponse = new CommentsResponse();
        commentsResponse.setTotal(commentsCount);

        // 收藏数量
        Long collectsCount = getArticleCollectsCountById(article.getId());
        detailArticleResponse.setCollects(collectsCount);

        // 是否点赞
        if (isLike(article.getId(), userId)) {
            detailArticleResponse.setIsLike(1);
        }
        // 是否收藏
        if (isCollect(article.getId(), userId)) {
            detailArticleResponse.setIsCollect(1);
        }

        // 获取评论数组
        List<ArticleComments> articleComments = getArticleCommentsById(article.getId());
        List<BaseComments> baseCommentsList = new ArrayList<>();
        for (ArticleComments comment : articleComments) {
            BaseComments baseComments = new BaseComments();
            baseComments.setId(comment.getUserId());
            User user = getArticleAuthorInfoById(comment.getUserId());
            baseComments.setName(user.getName());
            baseComments.setAvatar(user.getAvatar());
            baseComments.setComment(comment.getContent());
            baseComments.setTime(comment.getCreateTime());
            baseComments.setCommentId(comment.getId());
            baseCommentsList.add(baseComments);
        }
        commentsResponse.setComments(baseCommentsList);
        detailArticleResponse.setComments(commentsResponse);

        // 根据文章作者id查询作者信息
        User articleAuthorInfo = getArticleAuthorInfoById(article.getAuthorId());
        BaseUserResponse baseUserResponse = new BaseUserResponse();
        BeanUtils.copyProperties(articleAuthorInfo, baseUserResponse);
        detailArticleResponse.setUser(baseUserResponse);

        // 根据文章id查询文章对应评论

        return detailArticleResponse;
    }
}




