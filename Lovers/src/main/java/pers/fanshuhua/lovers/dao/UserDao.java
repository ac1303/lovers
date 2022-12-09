package pers.fanshuhua.lovers.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import pers.fanshuhua.lovers.enitiy.User;

/**
 * @author 小王的饭饭
 * @create 2022/11/25 17:52
 */
@Mapper
public interface UserDao {

    @Select("select * from user where open_id = #{openId}")
    @Results({
            @Result(property = "openId",column = "open_id"),
            @Result(property = "sessionKey",column = "session_key"),
            @Result(property = "competence",column = "competence")
    })
    User getUser(String openId);

    @Select("select * from user where session_key = #{Sessionkey}")
    @Results({
            @Result(property = "openId",column = "open_id"),
            @Result(property = "sessionKey",column = "session_key"),
            @Result(property = "competence",column = "competence")
    })
    User getUserBySessionkey(String sessionkey);

    @Insert("insert into user (open_id,session_key,competence) values (#{openId},#{sessionKey},#{competence})")
    int insertUser(User user);

    @Update("update user set session_key = #{sessionKey},competence = #{competence} where open_id = #{openId}")
    int updateUser(User user);
}
