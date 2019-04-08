package com.starry.wherego.dao;

import com.starry.wherego.bean.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ClassName: UserDao
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-03-28 19:19
 **/
@Repository
public interface UserDao extends JpaRepository<User,Long> {
    /**
     * 根据用户名和密码登陆
     * @param name
     * @param pwd
     * @return
     */
    User findUserByUserNameAndPwd(String name, String pwd);

    /**
     *
     * @param name
     * @return
     */
    User findUserByUserName(String name);

    @Override
    User save(User user);

    /**
     * 根据用户手机号获取用户
     * @param email
     * @return
     */
    User findUserByEmail(String email);

}
