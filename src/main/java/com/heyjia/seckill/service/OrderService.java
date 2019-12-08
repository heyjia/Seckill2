package com.heyjia.seckill.service;

import com.heyjia.seckill.dao.OrderDao;
import com.heyjia.seckill.domain.OrderInfo;
import com.heyjia.seckill.domain.SecKIllOrder;
import com.heyjia.seckill.domain.SecKillGoods;
import com.heyjia.seckill.domain.SeckillUser;
import com.heyjia.seckill.redis.OrderKey;
import com.heyjia.seckill.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class OrderService {

    @Autowired
    OrderDao orderDao;
    @Autowired
    RedisService redisService;
    Logger logger = LoggerFactory.getLogger(OrderService.class);
    public SecKIllOrder selectOrderByUserIdAndGoodsId(long userid, long goodsId) {
        if (orderDao == null) {
            logger.info("空");
        }
        logger.info("非空");
        SecKIllOrder order = redisService.get(OrderKey.ORDERINFOKEY,""+goodsId,SecKIllOrder.class);
        return order;
    }

    public OrderInfo createOrder(SeckillUser seckillUser, SecKillGoods goods) {
        OrderInfo order = new OrderInfo();
        order.setGoodsId(goods.getId());
        order.setGoodsName(goods.getGoodsName());
        order.setUserId(seckillUser.getId());
        order.setGoodsCount(1);
        order.setOrderChannel(1);
        order.setCreateDate(new Date());
        order.setGoodsPrice(goods.getSeckillPrice());
        order.setOrderStatus(0);
        long orderId = orderDao.InsertOrder(order);
        SecKIllOrder o = new SecKIllOrder();
        o.setOrderId(orderId);
        o.setUrerId(seckillUser.getId());
        o.setGoodsId(goods.getId());
        orderDao.insertSecKillOrder(o);
        redisService.set(OrderKey.ORDERINFOKEY,""+goods.getId(),o);
        return order;
    }
}
