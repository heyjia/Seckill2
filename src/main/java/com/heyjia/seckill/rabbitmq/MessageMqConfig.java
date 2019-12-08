package com.heyjia.seckill.rabbitmq;

import com.rabbitmq.client.AMQP;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class MessageMqConfig {
    public final static String QUEUE_NAME = "demoqueue";
    public final static String TOPIC_QUEUE_NAME1 = "topic1";
    public final static String TOPIC_QUEUE_NAME2 = "topic2";
    public final static String EXCHANGE_NAME1 = "exchange1";
    public final static String EXCHANGE_NAME2 = "exchange2";
    public final static String EXCHANGE_NAME3 = "exchange3";
    public static final String FANOUT_QUEUE_NAME1 = "fanout1";
    public static final String FANOUT_QUEUE_NAME2 = "fanout2";
    public static final String HEADER_QUEUE_NAME1 = "header1";
    public static final String SECKILL_QUEUE_NAME = "seckill.queue";


    @Bean
    public Queue queue(){
        return new Queue(SECKILL_QUEUE_NAME,true);
    }
//    @Bean
//    public Queue queue(){
//        return new Queue(MessageMqConfig.QUEUE_NAME,true);
//    }
//    @Bean
//    public Queue topicqueue1(){
//        return new Queue(MessageMqConfig.TOPIC_QUEUE_NAME1,true);
//    }
//    @Bean
//    public Queue topicqueue2(){
//        return new Queue(MessageMqConfig.TOPIC_QUEUE_NAME2,true);
//    }
//    @Bean
//    public TopicExchange exchange1(){
//        return new TopicExchange(EXCHANGE_NAME1);
//    }
//    @Bean
//    public Binding topicBinding1(){
//        return BindingBuilder.bind(topicqueue1()).to(exchange1()).with("topic.key1");
//    }
//    @Bean
//    public Binding topicBinding2(){
//        return BindingBuilder.bind(topicqueue2()).to(exchange1()).with("topic.#");
//    }
//
//    @Bean
//    public Queue fanoutqueue1(){
//        return new Queue(MessageMqConfig.FANOUT_QUEUE_NAME1,true);
//    }
//    @Bean
//    public Queue fanoutqueue2(){
//        return new Queue(MessageMqConfig.FANOUT_QUEUE_NAME2,true);
//    }
//    @Bean
//    public FanoutExchange exchange2(){
//        return new FanoutExchange(EXCHANGE_NAME2);
//    }
//    @Bean
//    public Binding FanoutBinding1() {
//        return BindingBuilder.bind(fanoutqueue1()).to(exchange2());
//    }
//    @Bean
//    public Binding FanoutBinding2() {
//        return BindingBuilder.bind(fanoutqueue2()).to(exchange2());
//    }
//
//    //Header模式
//    @Bean
//    public HeadersExchange exchange3(){
//        return new HeadersExchange(EXCHANGE_NAME3);
//    }
//
//    @Bean
//    public Queue headerqueue(){
//        return new Queue(HEADER_QUEUE_NAME1,true);
//    }
//    @Bean
//    public Binding bingHeaderExchange(){
//        Map<String,Object> map = new HashMap<>();
//        map.put("header1","value1");
//        map.put("header2","value2");
//        return BindingBuilder.bind(headerqueue()).to(exchange3()).whereAll(map).match();
//    }
}
