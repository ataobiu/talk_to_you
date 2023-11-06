package com.example.server;

import com.example.server.mapper.UserMapper;
import com.example.server.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: ataobiu
 * @Description: TODO
 * @DateTime: 2023/9/19 13:28
 **/
@SpringBootTest
public class MybatisTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testSelectList() {
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

}
