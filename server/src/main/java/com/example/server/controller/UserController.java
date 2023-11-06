package com.example.server.controller;

import cn.hutool.core.date.DateUtil;
import com.example.server.common.AuthAccess;
import com.example.server.request.BaseUserReq;
import com.example.server.request.RegisterUserReq;
import com.example.server.request.UpdateUserReqReq;
import com.example.server.response.Result;
import com.example.server.common.Token;
import com.example.server.model.User;
import com.example.server.response.UserResponse;
import com.example.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @Author: ataobiu
 * @Description: TODO
 * @DateTime: 2023/9/19 13:37
 **/
@CrossOrigin
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    final UserService userService;

    /**
     * 用户登录
     */
    @AuthAccess
    @PostMapping("login")
    public Result login(@RequestBody BaseUserReq baseUserReq) {
        User dbUser = userService.findUserByName(baseUserReq.getAccount());
        if (dbUser == null || !matchesPassword(baseUserReq.getPassword(), dbUser.getPassword())) {
            return Result.error("账号或密码错误");
        }
        String token;
        token = Token.genToken(String.valueOf(dbUser.getId()), dbUser.getPassword());
        HashMap<Object, Object> maps = new HashMap<>();
        // 根据用户id，和名字生成token,60分钟过期
        maps.put("token", token);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(dbUser, userResponse);
        maps.put("user", userResponse);
        return Result.success(maps);
    }

    /**
     * 用户注册
     */
    @AuthAccess
    @PostMapping("register")
    public Result registerUser(@RequestBody RegisterUserReq registerUser) {
        // 查询用户账号是否已经被占用
        User dbuser = userService.findUserByName(registerUser.getAccount());
        if (dbuser != null) return Result.error("账号已存在");
        // 注册用户
        User user = new User();
        BeanUtils.copyProperties(registerUser, user);
        user.setCreateTime(DateUtil.date());
        user.setPassword(encodePassword(user.getPassword()));
        // 注册成功
        if (userService.registerUser(user)) {
            User dbUser = userService.findUserByName(user.getAccount());
            // 根据数据库中的用户id，和密码生成token
            String token = Token.genToken(String.valueOf(dbUser.getId()), dbUser.getPassword());
            HashMap<Object, Object> maps = new HashMap<>();
            maps.put("token", token);
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(dbUser, userResponse);
            maps.put("user", userResponse);
            return Result.success("注册成功", maps);
        }
        return Result.error("注册失败");
    }

    /**
     * 用户注销
     */
    @PostMapping("delete")
    public Result deleteUser() {
        if (userService.deleteUser(Token.getUserId())) {
            return Result.success();
        }
        return Result.sys_error();
    }

    /**
     * 更新用户数据
     */
    @PostMapping("/update")
    public Result update(@RequestBody UpdateUserReqReq updateUserReq) {
        Long id = Token.getUserId();
        // 如果前端传了新密码，则对密码进行加密
        if (updateUserReq.getPassword() != null) {
            updateUserReq.setPassword(encodePassword(updateUserReq.getPassword()));
        }
        if (userService.updateUserById(id, updateUserReq)) {
            User dbUser = userService.getById(id);
            HashMap<Object, Object> maps = new HashMap<>();
            // 如果修改了密码 则重新生产token
//            if (updateUser.getPassword() != null){
//                Token.genToken(String.valueOf(dbUser.getId()), dbUser.getPassword());
//            }
            maps.put("token", Token.genToken(String.valueOf(dbUser.getId()), dbUser.getPassword()));
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(dbUser, userResponse);
            maps.put("user", userResponse);
            return Result.success("修改成功", maps);
        }
        return Result.error();
    }

    /**
     * 用户密码加密工具（来自springboot-security）
     */
    public String encodePassword(String rawPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(rawPassword);
    }

    public boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
