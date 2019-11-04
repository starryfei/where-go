package com.starry.wheregodata.service;

import com.starry.wheregodata.bean.dto.TravelContent;
import com.starry.wheregodata.bean.vo.TravelVo;

import java.util.List;

/**
 * ClassName: IndexService
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-11-04 22:30
 **/
public interface IndexService {
    List<TravelVo> index(int start, int end);

    List<TravelVo> allData();

    void insertData(TravelContent content);

    void deleteData(String userId, String docId);

    List<TravelVo> getDataByUserId(String userId);
}
