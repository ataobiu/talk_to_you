package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.ArticleCommentsMapper;
import com.example.server.model.ArticleComments;
import com.example.server.request.BaseCommentReq;
import com.example.server.service.ArticleCommentsService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author sherry
 * @description 针对表【article_comments(文章评论表)】的数据库操作Service实现
 * @createDate 2023-10-19 14:33:12
 */
@Service
public class ArticleCommentsServiceImpl extends ServiceImpl<ArticleCommentsMapper, ArticleComments>
        implements ArticleCommentsService {

    @Override
    public boolean addComment(BaseCommentReq comment, Long userId) {
        ArticleComments articleComment = new ArticleComments();
        articleComment.setArticleId(comment.getId());
        articleComment.setUserId(userId);
        articleComment.setContent(comment.getComment());
        articleComment.setCreateTime(new Date());
        return save(articleComment);
    }

    @Override
    public boolean deleteComment(String id, Long userId) {
        return lambdaUpdate()
                .eq(ArticleComments::getId, id)
                .eq(ArticleComments::getUserId, userId)
                .remove();
    }
}




