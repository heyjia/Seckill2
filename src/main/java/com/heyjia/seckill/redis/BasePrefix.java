package com.heyjia.seckill.redis;

public abstract class BasePrefix implements  KeyPerfix{
    private int exporeSeconds;
    private String prefix;

    public BasePrefix(String prefix){
        this(0,prefix);
    }
    public BasePrefix(int exporeSeconds, String prefix) {
        this.exporeSeconds = exporeSeconds;
        this.prefix = prefix;
    }

    @Override
    public int getExpireSeconds() {
        return exporeSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }
}
