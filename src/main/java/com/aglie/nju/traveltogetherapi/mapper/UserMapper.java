package com.aglie.nju.traveltogetherapi.mapper;
import java.util.List;

import org.apache.ibatis.annotations.*;

import com.aglie.nju.traveltogetherapi.model.UserInfo;
@Mapper
public interface UserMapper {
    /*******查询所有用户数据********/
    @Select("select * from users")
    List<UserInfo> selectUserByAll();

    /*******根据account查询符合用户********/
    @Select("select * from users where account = #{account}")
    UserInfo selectUserByAccount(String account);

    /*******根据name查询符合用户********/
    @Select("select * from users where name=#{name}")
    List<UserInfo> selectUserByName(String name);

    /*******添加新用户********/
    @Insert("insert into users(account,name,passwd) values (#{account},#{name},#{passwd})")
    int addUser(UserInfo user);

    /*******修改已有用户信息********/
    @Update("update users set name=#{name},gender=#{gender},age=#{age},city=#{city},code=#{code},passwd=#{passwd} where account=#{account}")
    int updateUser(UserInfo user);

    /*******修改已有用户正在进行的活动信息********/
    @Update("update users set activity_id=#{activity_id} where account=#{account}")
    int updateUserActivity(UserInfo user);

    /*******用户请求参加活动********/
    @Update("update users set activity_id=#{activity_id} where account=#{account}")
    int userAttendActivity(UserInfo user);

    /*******用户退出活动********/
    @Update("update users set activity_id=#{activity_id} where account=#{account}")
    int userQuitActivity(UserInfo user);

}
