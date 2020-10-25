package com.nobody.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author Mr.nobody
 * @Date 2020/10/25
 * @Version 1.0
 */
@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("login")
    public String login(@RequestParam String account, @RequestParam String password) {
        return "account:" + account + ",password:" + password;
    }
}

