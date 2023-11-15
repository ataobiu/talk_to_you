package com.example.server.controller;

import com.example.server.common.Token;
import com.example.server.response.Result;
import com.example.server.service.impl.ArticleCollectsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ataobiu
 * @Description: TODO
 * @DateTime: 2023/11/9 16:29
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/collects")
@RequiredArgsConstructor
public class CollectController {
    final ArticleCollectsServiceImpl articleCollectsService;

    /**
     * 收藏或取消收藏
     *
     * @param id
     * @return
     */
    @PostMapping("/{id}")
    public Result addOrDeleteLike(@PathVariable String id) {
        Long userId = Token.getUserId();
        if (articleCollectsService.addOrDeleteCollect(id, userId)) {
            return Result.success();
        }
        return Result.error();
    }
}
