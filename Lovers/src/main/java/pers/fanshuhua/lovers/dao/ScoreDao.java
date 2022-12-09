package pers.fanshuhua.lovers.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import pers.fanshuhua.lovers.enitiy.Score;

/**
 * @author 小王的饭饭
 * @create 2022/11/26 16:20
 */
@Mapper
public interface ScoreDao {

    /**
     * 得到分数
     *
     * @param openId 开放id
     * @return {@link Score}
     */
    @Select("select * from score where open_id = #{openId}")
    @Results({
            @Result(property = "openId", column = "open_id"),
            @Result(property = "loveOpenId", column = "lovers_open_id"),
            @Result(property = "score", column = "score"),
            @Result(property = "gold", column = "gold"),

    })
    Score getScore(String openId);
}
