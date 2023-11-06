package com.example.server.common;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.server.mapper.UserMapper;
import com.example.server.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class Token {

    private static UserMapper staticUserMapper;

    @Resource
    UserMapper userMapper;

    /**
     * 生成token
     *
     * @return
     */
    public static String genToken(String userId, String userPassword) {
        return JWT.create().withAudience(userId) // 将 user id 保存到 token 里面,作为载荷
                .withExpiresAt(DateUtil.offsetHour(DateTime.now(), 10)) // 10小时后token过期
                .withExpiresAt(DateUtil.offsetDay(DateTime.now(), 7))
                .sign(Algorithm.HMAC256(userPassword)); // 以 password 作为 token 的密钥
    }

    public static Long getUserId() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isNotBlank(token)) {
                Long userId = Long.valueOf(JWT.decode(token).getAudience().get(0));
                return userId;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    /**
     * 获取当前登录的用户信息
     *
     * @return user对象
     */
    public static User getCurrentUser() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isNotBlank(token)) {
                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserMapper.selectById(Long.valueOf(userId));
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    @PostConstruct
    public void setUserService() {
        staticUserMapper = userMapper;
    }
}
