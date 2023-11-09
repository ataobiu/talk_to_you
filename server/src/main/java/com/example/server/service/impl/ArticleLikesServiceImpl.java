package com.example.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.exception.ServiceException;
import com.example.server.model.ArticleLikes;
import com.example.server.service.ArticleLikesService;
import com.example.server.mapper.ArticleLikesMapper;
import lombok.val;
import net.sf.jsqlparser.expression.TryCastExpression;
import org.springframework.cglib.core.AbstractClassGenerator;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author sherry
 * @description 针对表【article_likes(文章点赞表)】的数据库操作Service实现
 * @createDate 2023-10-19 14:33:08
 */
@Service
public class ArticleLikesServiceImpl extends ServiceImpl<ArticleLikesMapper, ArticleLikes>
        implements ArticleLikesService {

    @Override
    public boolean addOrDeleteLike(String id, Long userId) {
        val one = lambdaQuery().eq(ArticleLikes::getArticleId, id)
                .eq(ArticleLikes::getUserId, userId)
                .one();
        if (one == null) {
            val articleLikes = new ArticleLikes();
            articleLikes.setArticleId(Long.valueOf(id));
            articleLikes.setUserId(userId);
            articleLikes.setCreateTime(new Date());
            return save(articleLikes);
        }
        return removeById(one);
    }
}