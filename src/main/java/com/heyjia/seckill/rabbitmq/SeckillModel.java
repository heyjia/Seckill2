package com.heyjia.seckill.rabbitmq;

import com.heyjia.seckill.domain.SeckillUser;

public class SeckillModel {
    public SeckillUser user;
    public long goodsId;

    @Override
    public String toString() {
        return "SeckillModel{" +
                "user=" + user +
                ", goodsId=" + goodsId +
                '}';
    }

    public SeckillModel(SeckillUser user, long goodsId) {
        this.user = user;
        this.goodsId = goodsId;
    }

    public void setUser(SeckillUser user) {
        this.user = user;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public SeckillUser getUser() {
        return user;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public SeckillModel() {
    }
}
