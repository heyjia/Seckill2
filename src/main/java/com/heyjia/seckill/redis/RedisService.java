package com.heyjia.seckill.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisService {
    @Autowired
    RedisConfig redisConfig;
    @Autowired
    JedisPool jedisPool;
    //获取某个对象
    public <T> T get(KeyPerfix prefix,String key,Class<T> clazz) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            String str = jedis.get(realKey);
            T t = stringToBean(str,clazz);
            return t;
        }finally {
            jedis.close();
         }
    }
    //设置对象
    public<T> boolean set(KeyPerfix prefix,String key,T value){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String str = BeanToString(value);
            if (str == null || str.length() <= 0) {
                return false;
            }
            String realKey = prefix.getPrefix() + key;
            int expireSeconds = prefix.getExpireSeconds();
            if (expireSeconds == 0) {
                jedis.set(realKey,str);
            }else{
                jedis.setex(realKey,expireSeconds,str);
            }

            return true;
        }finally {
            jedis.close();
        }
    }
    //判断key是否存在
    public<T> boolean exists(KeyPerfix prefix,String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            Boolean result = jedis.exists(realKey);
            return result;
        }finally {
            jedis.close();
        }
    }
    //加1
    public<T> Long incr(KeyPerfix prefix,String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.incr(realKey);
        }finally {
            jedis.close();
        }
    }
    //减一
    public<T> Long decr(KeyPerfix prefix,String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.decr(realKey);
        }finally {
            jedis.close();
        }
    }

    public static  <T> T stringToBean(String str,Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T)Integer.valueOf(str);
        }else if (clazz == String.class) {
                return (T) str;
        }else if(clazz == long.class || clazz == Long.class) {
            return (T)Long.valueOf(str);
        }else{
            return JSON.toJavaObject(JSON.parseObject(str),clazz);
        }
    }
    public static <T> String BeanToString(T t) {
        if (t == null) {
            return null;
        }
        Class clazz = t.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return t + "";
        }else if (clazz == String.class) {
            return (String)t;
        }else if(clazz == long.class || clazz == Long.class) {
            return t + "";
        }else{
            return JSON.toJSONString(t);
        }
    }

    @Bean
    public JedisPool JedisPoolFactory(){
        JedisPoolConfig jpc = new JedisPoolConfig();
        if (redisConfig == null) {
            System.out.println("空！");
        }
        System.out.println ("heijiatest"  + "" +redisConfig.getPoolMaxIdle());
        jpc.setMaxIdle(redisConfig.getPoolMaxIdle());
        jpc.setMaxTotal(redisConfig.getPoolMaxTotal());
        jpc.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);
        JedisPool jp = new JedisPool(jpc,redisConfig.getHost(),redisConfig.getPort(),redisConfig.getTimeout() * 1000,redisConfig.getPassword(),0);
        return jp;
    }
}
