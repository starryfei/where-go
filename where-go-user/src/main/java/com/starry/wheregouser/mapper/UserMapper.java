package com.starry.wheregouser.mapper;

import com.starry.wheregouser.bean.dto.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName: UserMapper
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-10-27 22:03
 **/
public interface UserMapper {

    @Select("Select * from user where user_name=#{name}")
    @Results(id="userMap",value = {
            @Result(column="user_name",property = "userName"),
            @Result(column = "user_pwd",property = "pwd"),
            @Result(column = "user_email",property = "email"),
            @Result(column = "phone",property = "telphone"),
            @Result(column = "user_icon",property = "icon")

    })
    User selectUserByName(String name);

    @Select("select * from user where user_email =#{email}")
    @ResultMap(value = "userMap")
    User selectUserByEmail(String email);
}
