package com.heyjia.seckill.rabbitmq;

import com.heyjia.seckill.domain.OrderInfo;
import com.heyjia.seckill.domain.SecKIllOrder;
import com.heyjia.seckill.domain.SecKillGoods;
import com.heyjia.seckill.domain.SeckillUser;
import com.heyjia.seckill.redis.RedisService;
import com.heyjia.seckill.result.CodeMsg;
import com.heyjia.seckill.service.GoodsService;
import com.heyjia.seckill.service.OrderService;
import com.heyjia.seckill.service.SecKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQRevecive {
    private static Logger logger = LoggerFactory.getLogger(MQRevecive.class);
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;
    @Autowired
    SecKillService secKillService;
    @RabbitListener(queues = MessageMqConfig.SECKILL_QUEUE_NAME)
    public void receiveMessge(String message){

        logger.info("消息"+ message);
        SeckillModel model = RedisService.stringToBean(message,SeckillModel.class);
        long goodsId = model.getGoodsId();
        SeckillUser user = model.getUser();
        logger.info("测试" + model.toString());
        //查询库存
        SecKillGoods goods = goodsService.getSecKillGoodsById(goodsId);
        int stock = goods.getGoodsStocks();
        if (stock <= 0) {
            return;
        }
        SecKIllOrder order = orderService.selectOrderByUserIdAndGoodsId(user.getId(),goodsId);
        if (order != null) {
            return;
        }
        secKillService.secKill(user,goods);
    }

//    @RabbitListener(queues = MessageMqConfig.QUEUE_NAME)
//    public void receiveMessge(String message){
//        logger.info(message);
//    }
//
//    @RabbitListener(queues = MessageMqConfig.TOPIC_QUEUE_NAME1)
//    public void receiveMessge2(String message){
//        logger.info(message + "topic1");
//    }
//    @RabbitListener(queues = MessageMqConfig.TOPIC_QUEUE_NAME2)
//    public void receiveMessge3(String message){
//        logger.info(message + "topic2");
//    }
//    @RabbitListener(queues = MessageMqConfig.FANOUT_QUEUE_NAME1)
//    public void receiveMessge4(String message){
//        logger.info(message + "fanout1");
//    }
//    @RabbitListener(queues = MessageMqConfig.FANOUT_QUEUE_NAME2)
//    public void receiveMessge5(String message){
//        logger.info(message + "fanout2");
//    }
//
//    @RabbitListener(queues = MessageMqConfig.HEADER_QUEUE_NAME1)
//    public void receiveMessge6(byte[] message){
//        logger.info(new String(message) + "fanout2");
//    }
}
