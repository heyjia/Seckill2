package com.heyjia.seckill.service;

import com.heyjia.seckill.Util.MD5Util;
import com.heyjia.seckill.Util.UUIDUtil;
import com.heyjia.seckill.domain.OrderInfo;
import com.heyjia.seckill.domain.SecKillGoods;
import com.heyjia.seckill.domain.SeckillUser;
import com.heyjia.seckill.redis.GoodsKey;
import com.heyjia.seckill.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecKillService {
    Logger logger = LoggerFactory.getLogger(SecKillService.class);
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;
    @Autowired
    RedisService redisService;
    @Transactional
    public OrderInfo secKill(SeckillUser seckillUser, SecKillGoods goods) {
        //减库存
        boolean result = goodsService.reduceStock(goods);
            if (result == true) {
                //生成订单
                OrderInfo order = orderService.createOrder(seckillUser,goods);
                return order;
            }else{
                return null;
        }

    }

    public String setPath(SeckillUser seckillUser, long goodsId) {
        String str = MD5Util.md5(UUIDUtil.uuid() + "123456");
        redisService.set(GoodsKey.miasohaPathKey,seckillUser.getId() + "_" + goodsId,str);
        logger.info(str);
        return str;
    }

    public boolean checkPath(SeckillUser seckillUser, long goodsId, String path) {
        if (seckillUser == null || path == null) {
            return false;
        }
        String OldPath = redisService.get(GoodsKey.miasohaPathKey,seckillUser.getId() + "_" + goodsId,String.class);
        return path.equals(OldPath);
    }
}
