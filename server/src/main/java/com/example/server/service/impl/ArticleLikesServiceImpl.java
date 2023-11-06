package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.model.ArticleLikes;
import com.example.server.service.ArticleLikesService;
import com.example.server.mapper.ArticleLikesMapper;
import org.springframework.stereotype.Service;

/**
* @author sherry
* @description 针对表【article_likes(文章点赞表)】的数据库操作Service实现
* @createDate 2023-10-19 14:33:08
*/
@Service
public class ArticleLikesServiceImpl extends ServiceImpl<ArticleLikesMapper, ArticleLikes>
    implements ArticleLikesService{

}




