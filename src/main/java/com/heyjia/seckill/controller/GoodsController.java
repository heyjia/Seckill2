package com.heyjia.seckill.controller;

import com.heyjia.seckill.domain.FormVo;
import com.heyjia.seckill.domain.Goods;
import com.heyjia.seckill.domain.SecKillGoods;
import com.heyjia.seckill.domain.SeckillUser;
import com.heyjia.seckill.redis.GoodsKey;
import com.heyjia.seckill.redis.RedisService;
import com.heyjia.seckill.redis.SeckillUserKey;
import com.heyjia.seckill.result.Result;
import com.heyjia.seckill.service.GoodsService;
import com.heyjia.seckill.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    private static Logger logger = LoggerFactory.getLogger(GoodsController.class);
    @Autowired
    GoodsService goodsService;
    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;
    @Autowired
    RedisService redisService;
    @Autowired
    ApplicationContext applicationContext;
    @RequestMapping(value = "/to_list",produces = "text/html")
    @ResponseBody
    public String toList(HttpServletRequest request,HttpServletResponse response,Model model, SeckillUser seckillUser){
        model.addAttribute("user",seckillUser);
        List<SecKillGoods> goodsList = goodsService.getSecKillGoods();
        model.addAttribute("goodsList",goodsList);
        String html = redisService.get(GoodsKey.goodsKey,"GoodsList",String.class);
//        if (!StringUtils.isEmpty(html))
//        {
//            return html;
//        }
        SpringWebContext ctx =new SpringWebContext(request,response,request.getServletContext(),request.getLocale(),model.asMap(),applicationContext);
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list",ctx);
        if (!StringUtils.isEmpty(html))
        {
            redisService.set(GoodsKey.goodsKey,"GoodsList",html);
        }
        return html;
    }
    @RequestMapping("to_detail/{goodsId}")
    public String toDetail(@PathVariable("goodsId") long goodId,Model model,SeckillUser seckillUser) {
        model.addAttribute("user",seckillUser);
        SecKillGoods goods = goodsService.getSecKillGoodsById(goodId);
        if (goods == null) {
            return "goods_error";
        }
        long startDate = goods.getStartDate().getTime() / 1000;
        long endDate = goods.getEndDate().getTime()/ 1000;
        long currentDate = System.currentTimeMillis()/ 1000;
        logger.info("start :" + startDate);
        logger.info("current" + currentDate);
        int secKillStatus = 0;  //0 秒杀未开始 1 秒杀进行中 2秒杀结束
        int beginRemainSeconds = 0;
        int endRemainSeconds = -1;
        if (startDate > currentDate) {
            secKillStatus = 0;
            beginRemainSeconds =  (int)(startDate - currentDate);
            endRemainSeconds = (int)(endDate - currentDate);
        }else if(currentDate > endDate){
            secKillStatus = 2;
            beginRemainSeconds = -1;
        }else{
            secKillStatus = 1;
            beginRemainSeconds = 0;
            endRemainSeconds = (int)(endDate - currentDate);
        }
        logger.info("beginRemainSeconds" + beginRemainSeconds);
        logger.info("endRemainSeconds" + endRemainSeconds);
        model.addAttribute("goods",goods);
        model.addAttribute("secKillStatus",secKillStatus);
        model.addAttribute("beginRemainSeconds",beginRemainSeconds);
        model.addAttribute("endRemainSeconds",endRemainSeconds);
        return "goods_detail";
    }
}
