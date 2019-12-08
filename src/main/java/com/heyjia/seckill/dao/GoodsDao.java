package com.heyjia.seckill.dao;

import com.heyjia.seckill.domain.SecKillGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GoodsDao {
    @Select("SELECT g.*,secg.id,secg.seckill_price,secg.seckill_count,secg.start_date,secg.end_date FROM seckill_goods secg LEFT JOIN goods g ON secg.goods_id = g.id")
    public List<SecKillGoods> getSecKillGoods();
    @Select("SELECT g.*,secg.id,secg.seckill_price,secg.seckill_count,secg.start_date,secg.end_date FROM seckill_goods secg LEFT JOIN goods g ON secg.goods_id = g.id where g.id = #{goodId}")
    public SecKillGoods getSecKillGoodsById(long goodId);
    @Update("UPDATE seckill_goods g SET g.seckill_count = g.seckill_count - 1 WHERE g.id = #{Id}")
    public int reduceStock(SecKillGoods goods);
}
