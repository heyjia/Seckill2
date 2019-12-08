package com.heyjia.seckill.domain;

import java.util.Date;

public class SecKillGoods extends Goods {
    private long Id;
    private double seckillPrice;
    private int seckillCount;
    private Date startDate;
    private Date endDate;

    @Override
    public String toString() {
        return super.toString() + "SecKillGoods{" +
                "Id=" + Id +
                ", seckillPrice=" + seckillPrice +
                ", seckillCount=" + seckillCount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    @Override
    public long getId() {
        return Id;
    }

    public double getSeckillPrice() {
        return seckillPrice;
    }

    public int getSeckillCount() {
        return seckillCount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @Override
    public void setId(long id) {
        Id = id;
    }

    public void setSeckillPrice(double seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public void setSeckillCount(int seckillCount) {
        this.seckillCount = seckillCount;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
