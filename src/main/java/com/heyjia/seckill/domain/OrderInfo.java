package com.heyjia.seckill.domain;

import java.util.Date;

public class OrderInfo {

    private long id;
    private long userId;
    private long goodsId;
    private long deliveryAddId;
    private String goodsName;
    private int goodsCount;
    private double goodsPrice;
    private int  orderChannel;
    private int orderStatus;
    private Date createDate;
    private Date payDate;

    public OrderInfo(long id, long userId, long goodsId, long deliveryAddId, String goodsName, int goodsCount, double goodsPrice, int orderChannel, int orderStatus, Date createDate, Date payDate) {
        this.id = id;
        this.userId = userId;
        this.goodsId = goodsId;
        this.deliveryAddId = deliveryAddId;
        this.goodsName = goodsName;
        this.goodsCount = goodsCount;
        this.goodsPrice = goodsPrice;
        this.orderChannel = orderChannel;
        this.orderStatus = orderStatus;
        this.createDate = createDate;
        this.payDate = payDate;
    }

    public OrderInfo() {
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public long getDeliveryAddId() {
        return deliveryAddId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public int getOrderChannel() {
        return orderChannel;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public void setDeliveryAddId(long deliveryAddId) {
        this.deliveryAddId = deliveryAddId;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public void setOrderChannel(int orderChannel) {
        this.orderChannel = orderChannel;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", goodsId=" + goodsId +
                ", deliveryAddId=" + deliveryAddId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsCount=" + goodsCount +
                ", goodsPrice=" + goodsPrice +
                ", orderChannel=" + orderChannel +
                ", orderStatus=" + orderStatus +
                ", createDate=" + createDate +
                ", payDate=" + payDate +
                '}';
    }
}
