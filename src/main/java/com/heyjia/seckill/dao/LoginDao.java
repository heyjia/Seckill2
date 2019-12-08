package com.heyjia.seckill.dao;

import com.heyjia.seckill.domain.SeckillUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginDao {
    @Select("select * from seckill_user where id = #{id}")
    public SeckillUser selectUserById(@Param("id") long id);
}
