package com.starry.wheregouser.mapper;

import com.starry.wheregouser.bean.dto.User;
import org.apache.ibatis.annotations.*;

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
    /**
     * 查找用户by name
     */
    User selectUserByName(String name);

    @Select("Select * from user where user_name=#{name} and user_pwd=#{pwd}")
    @ResultMap(value = "userMap")
    User login(String name,String pwd);

    @Select("select * from user where id =#{id}")
    @ResultMap(value = "userMap")
    User selectUserById(String id);

    @Insert("insert into user(user_name,user_pwd,user_email,phone,user_icon) " +
            "values(#{userName},#{pwd},#{email},#{phone},#{icon})")
    @ResultMap(value = "userMap")

    void saveUser(String userName,String pwd, String email, String phone, String icon);

}
