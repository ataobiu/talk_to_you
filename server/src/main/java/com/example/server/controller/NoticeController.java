package com.example.server.controller;

import com.example.server.common.AuthAccess;
import com.example.server.model.Notice;
import com.example.server.response.Result;
import com.example.server.service.impl.NoticeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: ataobiu
 * @Description: 获取通知
 * @DateTime: 2023/10/19 17:08
 **/
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
    final NoticeServiceImpl noticeService;
    @AuthAccess
    @GetMapping("")
    public Result getNotice() {
        List<Notice> list = noticeService.list();
        return Result.success(list);
    }
}
