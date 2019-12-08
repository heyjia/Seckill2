package com.heyjia.seckill.controller;

import com.heyjia.seckill.domain.FormVo;
import com.heyjia.seckill.result.CodeMsg;
import com.heyjia.seckill.result.Result;
import com.heyjia.seckill.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }
    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> dologin(HttpServletRequest request,HttpServletResponse response, FormVo vo){
        System.out.println("进入login");
        logger.info("进入login");
        boolean result = loginService.login(request,response,vo);
        return Result.success(true);
    }
}
