package com.cats.lostandfound.mapper;

import com.cats.lostandfound.entity.Photo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper //标记mapper文件位置，否则在Application.class启动类上配置mapper包扫描
@Repository
public interface PhotoMapper {

    @Insert("insert into photo values(#{photo_id}, #{post_id},#{path},#{index}, #{cat_class})")
    @Options(useGeneratedKeys = true,keyProperty = "photo_id",keyColumn = "photo_id")
    void addPhoto(Photo photo);

    @Select(value = "select * from photo where post_id=#{post_id} order by timestamp desc")
    @Results(id="photoMap", value={
            @Result(property = "photo_id",column = "photo_id"),
            @Result(property = "post_id",column = "post_id"),
            @Result(property = "path",column = "path"),
            @Result(property = "index",column = "index"),
            @Result(property = "cat_class",column = "cat_class")
    })
    List<Photo> findPhotosByPostId(@Param("post_id") long post_id);

    @Select(value = "select * from photo where post_id=#{post_id} and index = 0 order by timestamp desc")
    @ResultMap("photoMap")
    Photo findCoverByPostId(@Param("post_id") long post_id);

    @Delete("delete from photo where photo_id=#{photo_id}")
    void deleteByPhotoId(@Param("photo_id") long photo_id);

    @Delete("delete from photo where post_id=#{post_id}")
    void deleteByPostId(@Param("post_id") long post_id);

    @Select(value = "select * from photo where post_id is null")
    @ResultMap("photoMap")
    List<Photo> findNullPhotos();
}
