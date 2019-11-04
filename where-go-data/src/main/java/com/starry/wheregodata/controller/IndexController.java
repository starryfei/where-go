package com.starry.wheregodata.controller;

import com.starry.common.vo.CommonResult;
import com.starry.wheregodata.bean.dto.TravelContent;
import com.starry.wheregodata.bean.vo.TravelVo;
import com.starry.wheregodata.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * ClassName: IndexController
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-11-04 22:52
 **/
@RestController
@Slf4j
public class IndexController {
    @Autowired
    IndexService indexService;

    @GetMapping("/index")
    public CommonResult getAllData(){
        List<TravelVo> vos = indexService.allData();
        return CommonResult.success(vos);
    }

    @GetMapping("/data}")
    public CommonResult getAllData(@RequestParam("start") int start, @RequestParam("end") int end){
        List<TravelVo> vos =  indexService.index(start,end);
        return CommonResult.success(vos);
    }

    @GetMapping("/delete")
    public CommonResult delete(@RequestParam("docId") String docId){
        indexService.deleteData("",docId);
        return CommonResult.success();
    }

    @PostMapping("/insert")
    public CommonResult insertData(@RequestParam("title") String title,@RequestParam("content") String content,
                                   @RequestParam("image") String image, @RequestParam("location") String location,
                                   @RequestParam("label") String label){
        TravelContent travelContent = TravelContent.builder().docId(UUID.randomUUID().toString().substring(0,10))
                .title(title)
                .content(content)
                .image(image)
                .userId("1")
                .userName("yafei")
                .createDate("..................")
                .label(label)
                .location(location)
                .status(1).build();
        indexService.insertData(travelContent);
        return CommonResult.success();

    }
    @GetMapping("/userData")
    public CommonResult getDataById(){
        List<TravelVo> vos = indexService.getDataByUserId("");
        return CommonResult.success(vos);
    }

}
