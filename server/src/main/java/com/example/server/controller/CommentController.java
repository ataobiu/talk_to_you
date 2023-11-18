package com.example.server.controller;

import com.example.server.common.Token;
import com.example.server.request.BaseCommentReq;
import com.example.server.response.Result;
import com.example.server.service.impl.ArticleCommentsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ataobiu
 * @Description: TODO
 * @DateTime: 2023/11/9 12:37
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    final ArticleCommentsServiceImpl commentService;

    @PostMapping("add")
    public Result addComment(@RequestBody BaseCommentReq comment) {
        Long userId = Token.getUserId();
        if (commentService.addComment(comment, userId)) {
            return Result.success("评论成功");
        }
        return Result.error("评论失败");
    }

    @PostMapping("/delete/{id}")
    public Result deleteComment(@PathVariable String id) {
        Long userId = Token.getUserId();
        if (commentService.deleteComment(id, userId)) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }
}
