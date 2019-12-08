package com.heyjia.seckill.service;

import com.heyjia.seckill.Util.MD5Util;
import com.heyjia.seckill.Util.UUIDUtil;
import com.heyjia.seckill.dao.LoginDao;
import com.heyjia.seckill.domain.FormVo;
import com.heyjia.seckill.domain.SeckillUser;
import com.heyjia.seckill.exception.GlobalException;
import com.heyjia.seckill.redis.RedisService;
import com.heyjia.seckill.redis.SeckillUserKey;
import com.heyjia.seckill.result.CodeMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginService {
    public static final String COOKIE_NAME_TOKEN = "token";
    Logger logger = LoggerFactory.getLogger(LoginService.class);
    @Autowired
    LoginDao loginDao;
    @Autowired
    RedisService redisService;
    public SeckillUser selectUserById(Long id){
        if (loginDao == null) {
            System.out.println("空");
        }
        SeckillUser user = loginDao.selectUserById(id);
        return user;
    }
    public Boolean login(HttpServletRequest request,HttpServletResponse response, FormVo vo){
        logger.info("进入login");
        if (vo == null) {
            throw new GlobalException(CodeMsg.All_EMPTY);
        }
        String mobile = vo.getMobile();
        String pass = vo.getPassword();
        if (mobile == null || mobile.length() <= 0) {
            throw new GlobalException(CodeMsg.MOBILE_EMPTY);
        }
        if (pass == null || pass.length() <= 0) {
            throw new GlobalException(CodeMsg.PASSWORD_EMPTY);
        }
        SeckillUser user = selectUserById(Long.parseLong(mobile));
        if (user == null) {
            throw new GlobalException(CodeMsg.USER_NOTEXISTS);
        }
        String turePassword = user.getUserpassword();
        String salt = user.getSalt();
        String inputpass = MD5Util.ServerToDB(pass,salt);
        logger.info("turePassword"+turePassword);
        logger.info("salt" + salt);
        logger.info("inputpass" + inputpass);
        if (!turePassword.equals(inputpass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        addcookie(response,user);
        return true;
    }

    public SeckillUser getCookileUser(HttpServletResponse response,String key) {
        SeckillUser user  = redisService.get(SeckillUserKey.token,key,SeckillUser.class);
        if (user != null) {
            addcookie(response,user);
        }
        return user;
    }
    public void addcookie(HttpServletResponse response, SeckillUser user){
        String token = UUIDUtil.uuid();
        redisService.set(SeckillUserKey.token,token,user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN,token);
        if (SeckillUserKey.token.getExpireSeconds() != 0) {
            cookie.setMaxAge(SeckillUserKey.token.getExpireSeconds());
        }
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
