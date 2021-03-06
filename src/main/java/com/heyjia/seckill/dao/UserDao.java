package com.heyjia.seckill.dao;

import com.heyjia.seckill.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    @Insert("insert into user(id,name) values (#{id},#{name})")
    public int insert(User user);
    @Select("select * from user where id = #{id}")
    public User getUserById(@Param("id") int id);

}
