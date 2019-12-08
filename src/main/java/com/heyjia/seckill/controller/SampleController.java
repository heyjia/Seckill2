package com.heyjia.seckill.controller;

import com.heyjia.seckill.domain.User;
import com.heyjia.seckill.rabbitmq.MQSend;
import com.heyjia.seckill.redis.RedisService;
import com.heyjia.seckill.redis.UserKey;
import com.heyjia.seckill.result.Result;
import com.heyjia.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class SampleController {
    @Autowired
    UserService userService;
    @Autowired
    RedisService redisService;
    @Autowired
    MQSend mqSend;
    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name","heyjia");
        return "hello";
    }

    @RequestMapping("/mq")
    @ResponseBody
    public Result<String> mqdemo(){
        //mqSend.sendHeader("hello word");
        return Result.success("hello word");
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Result<User> selectUser(){
        User user = userService.getUserById(1);
        return Result.success(user);
    }
    @RequestMapping("/tx")
    @ResponseBody
    public Result<Boolean> tx(){
       Boolean result =  userService.tx();
       return  Result.success(result);

    }
    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet(){
        User user = new User(1,"userone");
        Boolean result = redisService.set(UserKey.getById,"1",user);
        return Result.success(result);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {
        User user  = redisService.get(UserKey.getById,"1",User.class);
        return Result.success(user);
    }

}
