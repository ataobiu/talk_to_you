package com.example.server.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ataobiu
 * @Description: mybatis-plus 拦截器
 * @DateTime: 2023/9/21 20:23
 **/
@Configuration
public class MapperConfig {
    @Bean
    public MybatisPlusInterceptor mpInterceptor() {
        //1.定义mapper拦截器
        MybatisPlusInterceptor mpInterceptor = new MybatisPlusInterceptor();
        //2.添加具体的拦截器
        //分页拦截器
        mpInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        //乐观拦截器
        mpInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return mpInterceptor;
    }
}