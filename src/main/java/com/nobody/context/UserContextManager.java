package com.nobody.context;

import com.nobody.enums.AuthenticationTypeEnum;
import com.nobody.exception.RestAPIError;
import com.nobody.exception.RestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.UUID;

/**
 * @Description 用户上下文操作类
 * @Author Mr.nobody
 * @Date 2020/10/25
 * @Version 1.0
 */
@Component
public class UserContextManager {

    private static final String COOKIE_KEY = "__userToken";

    // @Autowired
    // private RedisService redisService;

    /**
     * 获取用户上下文信息
     * 
     * @param request
     * @return
     */
    public UserContext getUserContext(HttpServletRequest request) {
        String userToken = getUserToken(request, COOKIE_KEY);
        if (!StringUtils.isEmpty(userToken)) {
            // 从缓存或者第三方获取用户信息
            // String userContextStr = redisService.getString(userToken);
            // if (!StringUtils.isEmpty(userContextStr)) {
            // return JSON.parseObject(userContextStr, UserContext.class);
            // }
            // 因为演示，没集成Redis，故简单new对象
            UserContext userContext = new UserContext();
            userContext.setName("Mr.nobody");
            userContext.setUserId("0000001");
            userContext.setType(AuthenticationTypeEnum.ADMIN);
            return userContext;
        }
        throw new RestException(RestAPIError.AUTH_ERROR);
    }

    public String getUserToken(HttpServletRequest request, String cookieKey) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (Objects.equals(cookie.getName(), cookieKey)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 保存用户上下文信息
     * 
     * @param response
     * @param userContextStr
     */
    public void saveUserContext(HttpServletResponse response, String userContextStr) {
        // 用户token实际根据自己业务进行生成，此处简单用UUID
        String userToken = UUID.randomUUID().toString();
        // 设置cookie
        Cookie cookie = new Cookie(COOKIE_KEY, userToken);
        cookie.setPath("/");
        response.addCookie(cookie);
        // redis缓存
        // redisService.setString(userToken, userContextStr, 3600);
    }

}
