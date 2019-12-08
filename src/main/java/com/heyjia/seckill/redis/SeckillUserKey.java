package com.heyjia.seckill.redis;

public class SeckillUserKey extends BasePrefix {
    private SeckillUserKey(String prefix) {
        super(prefix);
    }
    public static SeckillUserKey token = new SeckillUserKey("tk");
}
