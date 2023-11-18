package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.model.Notice;
import com.example.server.service.NoticeService;
import com.example.server.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

/**
* @author sherry
* @description 针对表【notice(通知栏信息表)】的数据库操作Service实现
* @createDate 2023-10-19 22:14:17
*/
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice>
    implements NoticeService{

}




