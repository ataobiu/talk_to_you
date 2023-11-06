package com.example.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.model.User;
import com.example.server.request.UpdateUserReqReq;
import com.example.server.service.UserService;
import com.example.server.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author sherry
 * @description 针对表【user(用户信息表)】的数据库操作Service实现
 * @createDate 2023-10-19 15:39:49
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Override
    // 根据账号查询用户信息
    public User findUserByName(String account) {
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<User>()
                .eq(User::getAccount, account);
        return getOne(query);
    }

    @Override
    // 注册用户
    public boolean registerUser(User user) {
        return save(user);
    }

    @Override
    // 根据id删除用户
    public boolean deleteUser(Long id) {
        return removeById(id);
    }

    @Override
    public boolean updateUserById(Long id, UpdateUserReqReq updateUserReq) {
        LambdaUpdateChainWrapper<User> set = lambdaUpdate()
                .eq(User::getId, id)
                .set(updateUserReq.getAccount() != null, User::getAccount, updateUserReq.getAccount())
                .set(updateUserReq.getPassword() != null, User::getPassword, updateUserReq.getPassword())
                .set(updateUserReq.getEmail() != null, User::getEmail, updateUserReq.getEmail())
                .set(updateUserReq.getName() != null, User::getName, updateUserReq.getName())
                .set(updateUserReq.getAvatar() != null, User::getAvatar, updateUserReq.getAvatar())
                .set(updateUserReq.getIntro() != null, User::getIntro, updateUserReq.getIntro());
        return update(set);
    }
}




