package pers.fanshuhua.lovers.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import pers.fanshuhua.lovers.enitiy.Lovers;

/**
 * @author 小王的饭饭
 * @create 2022/11/26 16:20
 */
@Mapper
public interface LoversDao {
    @Select("select * from lovers where open_id = #{openId} or lovers_open_id = #{openId}")
    @Results({
            @Result(property = "openId", column = "open_id"),
            @Result(property = "loversOpenId", column = "lovers_open_id"),
            @Result(property = "dateOfLove", column = "date_of_love"),
    })
    Lovers getLovers(String openId);
}
