package com.starry.wheregodata.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.starry.wheregodata.bean.dto.TravelContent;
import com.starry.wheregodata.bean.vo.TravelVo;
import com.starry.wheregodata.mapper.IndexMapper;
import com.starry.wheregodata.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: IndexServiceImpl
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-11-04 22:40
 **/
@Service
@Slf4j
public class IndexServiceImpl implements IndexService {
    @Autowired
    IndexMapper indexMapper;

    @Override
    public List<TravelVo> index(int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<TravelContent> results = indexMapper.index();
        log.info("total value: "+((Page)results).getTotal());
        return converToVo(results);
    }

    @Override
    public List<TravelVo> allData() {
        List<TravelContent> results = indexMapper.allData();
        return converToVo(results);
    }


    @Override
    public void insertData(TravelContent content) {
        indexMapper.insertData(content);
    }

    @Override
    public void deleteData(String userId, String docId) {
        indexMapper.deleteData(userId,docId);
    }

    @Override
    public List<TravelVo> getDataByUserId(String userId) {
        return null;
    }

    private List<TravelVo> converToVo(List<TravelContent> results) {
        List<TravelVo> vos = new ArrayList<>(results.size());
        results.forEach(res -> {
            TravelVo vo = TravelVo.builder().build();
            BeanUtils.copyProperties(res,vo);
            vos.add(vo);
        });
        return vos;
    }
}
