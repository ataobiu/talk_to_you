package com.example.server.controller;

import cn.hutool.core.io.FileUtil;
import com.example.server.common.AuthAccess;
import com.example.server.model.User;
import com.example.server.request.AddArticleReq;
import com.example.server.request.BaseArticleReq;
import com.example.server.request.UpdateArticleReq;
import com.example.server.response.BaseArticleResponse;
import com.example.server.response.DetailArticleResponse;
import com.example.server.response.Result;
import com.example.server.common.Token;
import com.example.server.model.Article;
import com.example.server.service.impl.ArticleServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author: ataobiu
 * @Description: TODO
 * @DateTime: 2023/9/20 21:46
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/article")
@RequiredArgsConstructor
public class ArticleController {
    final ArticleServiceImpl articleService;

    /**
     * 获取公开的内容
     *
     * @return
     */
    @AuthAccess
    @GetMapping("/explore")
    public Result getExploreArticle() {
        List<BaseArticleResponse> exploreArticle = articleService.getExploreArticle();
        return Result.success(exploreArticle);
    }

    /**
     * 获取个人用户的内容
     *
     * @return
     */
    @GetMapping("/private")
    public Result getPrivateArticle() {
        Long userId = Token.getUserId();
        List<BaseArticleResponse> privateArticleById = articleService.getPrivateArticleById(userId);
        return Result.success(privateArticleById);
    }

    /**
     * 修改个人文章内容
     */
    @PostMapping("/update")
    public Result updateArticleById(@RequestBody UpdateArticleReq updateArticle) {
        Long userId = Token.getUserId();
        Article article = new Article();
        BeanUtils.copyProperties(updateArticle, article);
        if (articleService.updateArticleById(article, userId)) {
            return Result.success();
        }
        return Result.error("修改失败，请检查");
    }

    /**
     * 删除个人文章内容
     */
    @PostMapping("/delete/{id}")
    public Result deleteArticleById(@PathVariable Long id) {
        Long userId = Token.getUserId();
        if (articleService.deleteArticleById(id, userId)) {
            return Result.success();
        }
        return Result.error();
    }

    /**
     * 获取用户点赞的文章
     *
     * @return
     */
    @GetMapping("/likes")
    public Result getLikeArticle() {
        Long userId = Token.getUserId();
        List<BaseArticleResponse> likeArticleById = articleService.getLikeArticleById(userId);
        return Result.success(likeArticleById);
    }

    /**
     * 获取用户收藏的文章
     *
     * @return
     */
    @GetMapping("/collects")
    public Result getCollectdArticle() {
        Long userId = Token.getUserId();
        List<BaseArticleResponse> collectdArticleById = articleService.getCollectdArticleById(userId);
        return Result.success(collectdArticleById);
    }

    /**
     * 获取文章详细内容
     *
     * @param id
     * @return
     */
    @AuthAccess
    @GetMapping("detail/{id}")
    public Result getDetailByArticleId(@PathVariable Long id) {
        System.out.println(id);
        Long userId = Token.getUserId();
        if (userId != null) {
            DetailArticleResponse detailByArticleId = articleService.getDetailByArticleId(id, userId);
            if (detailByArticleId == null) {
                return Result.auth_error();
            }
            return Result.success(detailByArticleId);
        }
        DetailArticleResponse detailByArticleId = articleService.getDetailByArticleId(id);
        return Result.success(detailByArticleId);
    }

    /**
     * 新增文章
     *
     * @return
     */
    @PostMapping("/add")
    public Result addArticle(@RequestBody AddArticleReq addArticleReq) {
        val userId = Token.getUserId();
        articleService.addArticle(userId, addArticleReq);
        return Result.success();
    }

    /**
     * 搜索文章
     * @param title
     * @return
     */
    @PostMapping("/search/{title}")
    public Result searchArticle(@PathVariable String title) {
        val baseArticleResponses = articleService.searchArticle(title);
        return Result.success(baseArticleResponses);
    }
}
