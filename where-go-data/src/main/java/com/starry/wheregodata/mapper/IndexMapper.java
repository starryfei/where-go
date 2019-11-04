package com.starry.wheregodata.mapper;

import com.starry.wheregodata.bean.dto.TravelContent;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * ClassName: IndexMapper
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-11-04 21:41
 **/

public interface IndexMapper {
    @Results(id = "indexMapper", value = {
            @Result(column = "doc_id", property = "docId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "content_label", property = "label")
    })
    @Select("select * from travel  where status=1 limit #{start}, #{end}")
    List<TravelContent> index(int start, int end);

    @Select("select * from travel where status=1")
    @ResultMap(value = "indexMapper")
    List<TravelContent> allData();

    @Insert({"insert into travel (doc_id,title,content,image,user_id,user_name,content_label,location,create_date,status) " +
            "values(#{docId},#{title},#{content},#{image},#{userId},#{userName},#{label},#{location}" +
            ",#{createDate},#{status})"})
    void insertData(TravelContent content);

    @Update("update travel set status=0 where user_id =#{userId} and doc_id=#{docId}")
    void deleteData(String userId, String docId);

    @Select("select * from travel where status=1")
    @ResultMap(value = "indexMapper")
    List<TravelContent> getDataByUserId(String userId);
}
