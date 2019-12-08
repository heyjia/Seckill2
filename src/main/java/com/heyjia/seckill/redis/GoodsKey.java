package com.heyjia.seckill.redis;

public class GoodsKey extends BasePrefix{
    private GoodsKey(String perfix){
        super(perfix);
    }
    public static GoodsKey goodsKey = new GoodsKey("gl");
    public static GoodsKey goodsSeckillKey = new GoodsKey("gs");
    public static GoodsKey miasohaPathKey = new GoodsKey("sp");

}
