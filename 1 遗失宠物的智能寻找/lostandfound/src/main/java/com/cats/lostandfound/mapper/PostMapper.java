package com.cats.lostandfound.mapper;

import com.cats.lostandfound.entity.Filter;
import com.cats.lostandfound.entity.Post;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper //标记mapper文件位置，否则在Application.class启动类上配置mapper包扫描
@Repository
public interface PostMapper {

    @Insert("insert into post values(#{post_id}, #{user_id},#{location},#{title},#{description},#{cat_class},#{type},#{status},#{cover_path})")
    @Options(useGeneratedKeys = true,keyProperty = "post_id",keyColumn = "post_id")
    public long post(Post post);

    @Select(value = "select * from post where user_id=#{user_id} order by timestamp desc")
    @Results(id="postMap", value={
            @Result(property = "post_id",column = "post_id"),
            @Result(property = "user_id",column = "user_id"),
            @Result(property = "location",column = "location"),
            @Result(property = "title",column = "title"),
            @Result(property = "description",column = "description"),
            @Result(property = "cat_class",column = "cat_class"),
            @Result(property = "type",column = "type"),
            @Result(property = "status",column = "status"),
            @Result(property = "cover_path",column = "cover_path"),
            @Result(property = "timestamp",column = "timestamp")
    })
    public List<Post> findPostsByUserId(@Param("user_id") long user_id);

    @Update("update post set location=#{location}, title=#{title}, " +
            "description = #{description}, cat_class = #{cat_class}, " +
            "type = #{type}, status = #{status}, cover_path = #{cover_path}, " +
            "timestamp = #{timestamp} " +
            " where post_id=#{post_id} ")
    public void updatePost(@Param("post_id")long post_id, @Param("location")int location, @Param("title")String title,
                                     @Param("description")String description, @Param("cat_class")int cat_class,
                                  @Param("type")int type, @Param("status")int status, @Param("cover_path") String cover_path,
                          @Param("timestamp") long timestamp);

    @Update("update post set cover_path = #{cover_path} where post_id=#{post_id} ")
    public int updatePostCoverPath(@Param("post_id")long post_id, @Param("cover_path") String cover_path);

    @Delete("delete from post where post_id=#{post_id}")
    public void deleteByPostId(@Param("post_id") long post_id);

    @Select({"<script>",
            " select * from post ",
            " where 1=1 ",
            "<when test='location!=null'>",
            " and location = #{location} ",
            "</when>",
            "<when test='cat_class!=null'>",
            " and cat_class = #{cat_class} ",
            "</when>",
            "<when test='type!=null'>",
            " and type = #{type} ",
            "</when>",
            "<when test='status!=null'>",
            " and status = #{status} ",
            "</when>",
            "<when test='startTime!=null'>",
            " and timestamp > #{startTime} ",
            "</when>",
            "<when test='endTime!=null'>",
            " and timestamp <= #{endTime} ",
            "</when>",
            " order by timestamp desc ",
            " limit #{offset},#{page_size} ",
            "</script>"})
    @ResultMap("postMap")
    public List<Post> findPostsByFilters(Filter filter);

}
