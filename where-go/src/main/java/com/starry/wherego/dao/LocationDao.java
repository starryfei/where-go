package com.starry.wherego.dao;

import com.starry.wherego.bean.dto.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName: LocationDao
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-03-27 23:18
 **/

@Repository
public interface LocationDao extends JpaRepository<Location,Long> {

   @Override
   Location save(Location location);

   /**
    * 根据UserID获取所有该用户的旅行位置信息
    * @param id
    * @return
    */
   List<Location> findAllByUserId(String id);
}
