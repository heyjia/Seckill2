package com.heyjia.seckill.redis;

public class OrderKey extends BasePrefix{
    private OrderKey(String prefix) {
        super(prefix);
    }
    public static OrderKey ORDERINFOKEY = new OrderKey("oi");
}
