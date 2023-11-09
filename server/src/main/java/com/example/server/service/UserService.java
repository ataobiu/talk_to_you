package com.example.server.service;

import com.example.server.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.request.UpdateUserReq;

/**
 * @author sherry
 * @description 针对表【user(用户信息表)】的数据库操作Service
 * @createDate 2023-10-19 15:39:49
 */
public interface UserService extends IService<User> {

    User findUserByName(String account);

    boolean registerUser(User user);

    boolean deleteUser(Long id);

    /**
     * 根据用户id，更新用户
     *
     * @param id         用户id
     * @param updateUserReq 用户信息
     * @return 更新结果
     */
    boolean updateUserById(Long id, UpdateUserReq updateUserReq);
}
