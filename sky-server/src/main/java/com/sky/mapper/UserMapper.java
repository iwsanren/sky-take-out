package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /**
     * retrieve a user by openid
     * @param openid
     * @return
     */
    @Select("select * from user where openid = #{openid}")
    User getByOpenId(String openid);

    /**
     * insert user record
     * @param user
     */
    void insert(User user);

    /**
     * get user by id
     * @param userId
     * @return
     */
    @Select("select * from user where id = #{userId}")
    User getById(Long userId);

}
