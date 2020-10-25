package com.nobody.controller;

import com.alibaba.fastjson.JSON;
import com.nobody.annotation.UserAuthenticate;
import com.nobody.context.UserContext;
import com.nobody.context.UserContextManager;
import com.nobody.enums.AuthenticationTypeEnum;
import com.nobody.pojo.model.GeneralResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @Author Mr.nobody
 * @Date 2020/10/25
 * @Version 1.0
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserContextManager userContextManager;

    @GetMapping("login")
    public GeneralResult<UserContext> doLogin(HttpServletResponse response) {
        UserContext userContext = new UserContext();
        userContext.setUserId("0000001");
        userContext.setName("Mr.nobody");
        userContext.setType(AuthenticationTypeEnum.ADMIN);
        userContextManager.saveUserContext(response, JSON.toJSONString(userContext));
        return GeneralResult.genSuccessResult(userContext);
    }

    @GetMapping("personal")
    @UserAuthenticate(permission = true, type = AuthenticationTypeEnum.ADMIN)
    public GeneralResult<UserContext> getPersonInfo(UserContext userContext) {
        return GeneralResult.genSuccessResult(userContext);
    }
}

