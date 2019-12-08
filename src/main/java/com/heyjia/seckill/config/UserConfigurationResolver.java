package com.heyjia.seckill.config;

import com.heyjia.seckill.domain.SeckillUser;
import com.heyjia.seckill.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserConfigurationResolver implements HandlerMethodArgumentResolver {
    @Autowired
    LoginService loginService;
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> clazz = methodParameter.getParameterType();
        return clazz == SeckillUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response =  nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        String paramvalue = request.getParameter(LoginService.COOKIE_NAME_TOKEN);
        Cookie[] cookies = request.getCookies();
        String cookievalue = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(LoginService.COOKIE_NAME_TOKEN)) {
                cookievalue = cookie.getValue();
            }
        }
        if (cookievalue == null && paramvalue == null) {
            return "login";
        }
        String key = paramvalue == null ? cookievalue : paramvalue;
        SeckillUser seckillUser = loginService.getCookileUser(response,key);
        return seckillUser;
    }
}
