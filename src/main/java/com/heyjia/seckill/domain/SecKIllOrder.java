package com.heyjia.seckill.domain;
public class SecKIllOrder {
    private long id;
    private long urerId;
    private long orderId;
    private long goodsId;
    public SecKIllOrder(long id, long urerId, long orderId, long goodsId) {
        this.id = id;
        this.urerId = urerId;
        this.orderId = orderId;
        this.goodsId = goodsId;
    }

    public SecKIllOrder() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUrerId(long urerId) {
        this.urerId = urerId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public long getId() {
        return id;
    }

    public long getUrerId() {
        return urerId;
    }

    public long getOrderId() {
        return orderId;
    }

    public long getGoodsId() {
        return goodsId;
    }
}
