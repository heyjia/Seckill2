package com.heyjia.seckill.domain;

public class Goods {
    private long id;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private String goodsDetail;
    private double goodsPrice;
    private int goodsStocks;

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", goodsTitle='" + goodsTitle + '\'' +
                ", goodsImg='" + goodsImg + '\'' +
                ", goodsDetail='" + goodsDetail + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", goodsStocks=" + goodsStocks +
                '}';
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public void setGoodsStocks(int goodsStocks) {
        this.goodsStocks = goodsStocks;
    }

    public long getId() {
        return id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public int getGoodsStocks() {
        return goodsStocks;
    }

    public Goods() {
    }

    public Goods(long id, String goodsName, String goodsTitle, String goodsImg, String goodsDetail, double goodsPrice, int goodsStocks) {
        this.id = id;
        this.goodsName = goodsName;
        this.goodsTitle = goodsTitle;
        this.goodsImg = goodsImg;
        this.goodsDetail = goodsDetail;
        this.goodsPrice = goodsPrice;
        this.goodsStocks = goodsStocks;
    }
}
