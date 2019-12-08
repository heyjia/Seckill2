package com.heyjia.seckill.dao;

import com.heyjia.seckill.domain.OrderInfo;
import com.heyjia.seckill.domain.SecKIllOrder;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderDao {
    @Select("SELECT * FROM  seckill_order o WHERE o.user_id = #{userId} and o.goods_id = #{goodsId}")
    public SecKIllOrder selectOrderByUserIdAndGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);

    @Insert("insert into order_info(user_id,goods_id,delivery_add_id,goods_name,goods_count,goods_price,order_channel,order_status," +
            "create_date,pay_date) values(#{userId},#{goodsId},#{deliveryAddId},#{goodsName},#{goodsCount},#{goodsPrice}," +
            "#{orderChannel},#{orderStatus},#{createDate},#{payDate})")
    @SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")
    public long InsertOrder(OrderInfo order);

    @Insert("insert into seckill_order(user_id,order_id,goods_id) values(#{urerId},#{orderId},#{goodsId})")
    public int insertSecKillOrder(SecKIllOrder o);
}
