package com.heyjia.seckill.rabbitmq;

import com.heyjia.seckill.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQSend {
    private static Logger logger = LoggerFactory.getLogger(MQSend.class);
    @Autowired
    AmqpTemplate amqpTemplate;


    public void seckillRequest(Object message) {
        logger.info("发送" + message);
        String msg =RedisService.BeanToString(message);
        amqpTemplate.convertAndSend(MessageMqConfig.SECKILL_QUEUE_NAME,msg);
    }
//    public void sendMessage(Object message) {
//        String msg = RedisService.BeanToString(message);
//        logger.info(msg);
//        amqpTemplate.convertAndSend(MessageMqConfig.QUEUE_NAME,msg);
//    }
//    //topic
//    public void sendMessage2(Object message) {
//        String msg = RedisService.BeanToString(message);
//        logger.info(msg);
//        amqpTemplate.convertAndSend(MessageMqConfig.EXCHANGE_NAME1,"topic.key1",msg + "1");
//        amqpTemplate.convertAndSend(MessageMqConfig.EXCHANGE_NAME1,"topic.key2",msg + "2");
//    }
//    //fanout
//    public void sendMessage3(Object message) {
//        String msg = RedisService.BeanToString(message);
//        logger.info(msg);
//        amqpTemplate.convertAndSend(MessageMqConfig.EXCHANGE_NAME2,"",msg + "1");
//        amqpTemplate.convertAndSend(MessageMqConfig.EXCHANGE_NAME2,"",msg + "2");
//    }
//    //header
//    public void sendHeader(Object message) {
//        String msg = RedisService.BeanToString(message);
//        logger.info(msg);
//        MessageProperties properties = new MessageProperties();
//        properties.setHeader("header1","value1");
//        properties.setHeader("header2","value2");
//        Message obj = new Message(msg.getBytes(),properties);
//        amqpTemplate.convertAndSend(MessageMqConfig.EXCHANGE_NAME3,"",obj);
//    }
}
