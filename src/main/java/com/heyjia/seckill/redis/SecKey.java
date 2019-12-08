package com.heyjia.seckill.redis;

public class SecKey extends BasePrefix {
    private SecKey(int exporeSeconds, String prefix) {
        super(exporeSeconds,prefix);
    }
    public static SecKey countKey = new SecKey(5000,"vc");
}
