package pers.fanshuhua.lovers.dao;

import org.apache.ibatis.annotations.*;
import pers.fanshuhua.lovers.enitiy.ScoreHistory;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author 小王的饭饭
 * @create 2022/11/26 16:20
 */
@Mapper
public interface ScoreHistoryDao {
    @Select("select * from score_history order by time desc limit #{start},#{end} ")
    @Results({
            @Result(property = "uuid", column = "uuid"),
            @Result(property = "openId", column = "open_id"),
            @Result(property = "loversOpenId", column = "lovers_open_id"),
            @Result(property = "operation", column = "operation"),
            @Result(property = "score", column = "score"),
            @Result(property = "reason", column = "reason"),
            @Result(property = "time", column = "time"),
    })
    List<ScoreHistory> getScoreHistory(int start, int end);
    @Insert("insert into score_history (uuid,open_id,lovers_open_id,operation,score,reason,time) values (#{uuid},#{openId},#{loversOpenId},#{operation},#{score},#{reason},#{time})")
    int insertScoreHistory(ScoreHistory scoreHistory);

//    检查今天是否有签到
    @Select("select * from score_history where open_id = #{openId} and lovers_open_id = #{loversOpenId} and reason = #{reason} and time >= #{time} and operation = 1")
    @Results({
            @Result(property = "uuid", column = "uuid"),
            @Result(property = "openId", column = "open_id"),
            @Result(property = "loversOpenId", column = "lovers_open_id"),
            @Result(property = "operation", column = "operation"),
            @Result(property = "score", column = "score"),
            @Result(property = "reason", column = "reason"),
            @Result(property = "time", column = "time"),
    })
    List<ScoreHistory> checkTodaySign(String openId, String loversOpenId, String reason, Timestamp time);
}
