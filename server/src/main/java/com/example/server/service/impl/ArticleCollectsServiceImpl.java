package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.model.ArticleCollects;
import com.example.server.model.ArticleLikes;
import com.example.server.service.ArticleCollectsService;
import com.example.server.mapper.ArticleCollectsMapper;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author sherry
 * @description 针对表【article_collects(文章收藏表)】的数据库操作Service实现
 * @createDate 2023-10-20 10:49:34
 */
@Service
public class ArticleCollectsServiceImpl extends ServiceImpl<ArticleCollectsMapper, ArticleCollects>
        implements ArticleCollectsService {

    @Override
    public boolean addOrDeleteCollect(String id, Long userId) {
        val one = lambdaQuery()
                .eq(ArticleCollects::getArticleId, id)
                .eq(ArticleCollects::getUserId, userId)
                .one();
        if (one == null) {
            val articleCollects = new ArticleCollects();
            articleCollects.setArticleId(Long.valueOf(id));
            articleCollects.setUserId(userId);
            articleCollects.setCreateTime(new Date());
            return save(articleCollects);
        }
        return removeById(one);
    }
}




