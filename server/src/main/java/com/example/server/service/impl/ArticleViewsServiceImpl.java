package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.model.ArticleViews;
import com.example.server.service.ArticleViewsService;
import com.example.server.mapper.ArticleViewsMapper;
import org.springframework.stereotype.Service;

/**
* @author sherry
* @description 针对表【article_views(文章浏览表)】的数据库操作Service实现
* @createDate 2023-10-19 14:33:04
*/
@Service
public class ArticleViewsServiceImpl extends ServiceImpl<ArticleViewsMapper, ArticleViews>
    implements ArticleViewsService{

}




