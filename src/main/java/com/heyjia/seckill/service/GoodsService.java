package com.heyjia.seckill.service;

import com.heyjia.seckill.dao.GoodsDao;
import com.heyjia.seckill.domain.Goods;
import com.heyjia.seckill.domain.SecKillGoods;
import com.heyjia.seckill.exception.GlobalException;
import com.heyjia.seckill.result.CodeMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GoodsService {
    @Autowired
    GoodsDao goodsDao;

    Logger logger = LoggerFactory.getLogger(GoodsService.class);
    public List<SecKillGoods> getSecKillGoods() {
        List<SecKillGoods> goodsList = goodsDao.getSecKillGoods();
        return goodsList;
    }

    public SecKillGoods getSecKillGoodsById(long goodId) {
        SecKillGoods goods = goodsDao.getSecKillGoodsById(goodId);
        if(goods == null) {
            throw new GlobalException(CodeMsg.GOODS_NOTEXISTS);
        }
        logger.info(goods.toString());
        return goods;
    }

    public boolean reduceStock(SecKillGoods goods) {
        int id =  goodsDao.reduceStock(goods);
        return id > 0;
    }
}
