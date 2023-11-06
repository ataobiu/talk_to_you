package com.example.server.common;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthAccess {
}