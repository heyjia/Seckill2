package com.heyjia.seckill.controller;

import com.heyjia.seckill.Util.MD5Util;
import com.heyjia.seckill.Util.UUIDUtil;
import com.heyjia.seckill.domain.*;
import com.heyjia.seckill.rabbitmq.MQSend;
import com.heyjia.seckill.rabbitmq.SeckillModel;
import com.heyjia.seckill.redis.GoodsKey;
import com.heyjia.seckill.redis.RedisService;
import com.heyjia.seckill.redis.SecKey;
import com.heyjia.seckill.result.CodeMsg;
import com.heyjia.seckill.result.Result;
import com.heyjia.seckill.service.GoodsService;
import com.heyjia.seckill.service.LoginService;
import com.heyjia.seckill.service.OrderService;
import com.heyjia.seckill.service.SecKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/scdkill")
public class SecKillController implements InitializingBean {
    @Autowired
    SecKillService seckillService;
    @Autowired
    OrderService orderService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    RedisService redisService;

    @Autowired
    MQSend mqSend;
    private static Logger logger = LoggerFactory.getLogger(SecKillController.class);
    @Override
    public void afterPropertiesSet() throws Exception {
        List<SecKillGoods> list =  goodsService.getSecKillGoods();
        for (SecKillGoods good : list) {
            redisService.set(GoodsKey.goodsSeckillKey,""+good.getId(),good.getSeckillCount());
        }
    }
    @RequestMapping(value = "/path")
    @ResponseBody
    public Result<String> getSecKillPath(SeckillUser seckillUser, @RequestParam("goodsId") long goodsId){
        System.out.println("123");
        if (seckillUser == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        String str = seckillService.setPath(seckillUser,goodsId);
        return Result.success(str);
    }
    @RequestMapping("/{path}/do_secKill")
    @ResponseBody
    public Result<Integer> doSecKill(HttpServletRequest request,Model model, SeckillUser seckillUser, @RequestParam("goodsId") long goodsId,
                                     @PathVariable("path") String path){
        model.addAttribute("user",seckillUser);
        boolean result = seckillService.checkPath(seckillUser,goodsId,path);
        if (!result) {
            return Result.error(CodeMsg.ERROR_PATH);
        }
        logger.info("秒杀地址正确");
        if (seckillUser == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        long userid = seckillUser.getId();
        Integer count = redisService.get(SecKey.countKey,"" + userid,Integer.class);
        if (count == null) {
            redisService.set(SecKey.countKey,"" + userid,1);
        } else{
            logger.info("秒杀次数 ： " + count);
            if (count >= 5) {
                return Result.error(CodeMsg.VISIT_TO_BUSY);
            }else{
                redisService.incr(SecKey.countKey,"" + userid);
            }
        }
//        int stock =redisService.get(GoodsKey.goodsSeckillKey,""+goodsId,int.class);
//        if (stock <= 0) {
//            return Result.error(CodeMsg.STOCK_NOT_ENOUGH);
//        }
//        //发送消息到RabbitMQ
//        SeckillModel ueserandgoods = new SeckillModel();
//        ueserandgoods.setGoodsId(goodsId);
//        ueserandgoods.setUser(seckillUser);
//        mqSend.seckillRequest(ueserandgoods);
        return Result.success(0);
//        //查询库存
//        SecKillGoods goods = goodsService.getSecKillGoodsById(goodsId);
//        if (goods.getSeckillCount() <= 0) {
//            model.addAttribute("msg", CodeMsg.STOCK_NOT_ENOUGH);
//            return "secKill_error";
//        }
//        //查询是否已经秒杀到了
//        SecKIllOrder order = orderService.selectOrderByUserIdAndGoodsId(seckillUser.getId(),goodsId);
//        if (order != null) {
//            model.addAttribute("msg", CodeMsg.REPEAT_SECKILL);
//            return "secKill_error";
//        }
//        logger.info("秒杀");
//        //减库存 生成订单 返回订单
//        OrderInfo orderInfo = seckillService.secKill(seckillUser,goods);
//        logger.info(orderInfo.toString());
//        model.addAttribute("order",orderInfo);
//        model.addAttribute("goods",goods);
//        return "order_detail";order_detail
    }

}
