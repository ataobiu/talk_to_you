package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.model.ArticleComments;
import com.example.server.service.ArticleCommentsService;
import com.example.server.mapper.ArticleCommentsMapper;
import org.springframework.stereotype.Service;

/**
* @author sherry
* @description 针对表【article_comments(文章评论表)】的数据库操作Service实现
* @createDate 2023-10-19 14:33:12
*/
@Service
public class ArticleCommentsServiceImpl extends ServiceImpl<ArticleCommentsMapper, ArticleComments>
    implements ArticleCommentsService{

}




