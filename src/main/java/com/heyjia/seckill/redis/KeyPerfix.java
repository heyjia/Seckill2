package com.heyjia.seckill.redis;

public interface KeyPerfix {
    public int getExpireSeconds();
    public String getPrefix();
}
