package com.example.server.controller;

import com.example.server.common.Token;
import com.example.server.response.Result;
import com.example.server.service.impl.ArticleLikesServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ataobiu
 * @Description: TODO
 * @DateTime: 2023/11/9 16:29
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/like")
@RequiredArgsConstructor
public class LikeController {
    final ArticleLikesServiceImpl articleLikesService;

    /**
     * 点赞或取消点赞
     *
     * @param id
     * @return
     */
    @PostMapping("/{id}")
    public Result addOrDeleteLike(@PathVariable String id) {
        Long userId = Token.getUserId();
        if (articleLikesService.addOrDeleteLike(id, userId)) {
            return Result.success();
        }
        return Result.error();
    }
}
