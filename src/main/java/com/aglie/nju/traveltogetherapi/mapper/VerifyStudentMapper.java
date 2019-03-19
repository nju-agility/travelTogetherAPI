package com.aglie.nju.traveltogetherapi.mapper;

import com.aglie.nju.traveltogetherapi.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface VerifyStudentMapper {

    /*******修改用户status状态********/
    @Update("update users set status=#{status} where account=#{account}")
    public int changeStudentStatus(UserInfo user);

    /*******管理员根据status查询用户********/
    @Select("select * from users where status = 1")
    public List<UserInfo> selectUserByStatus();
}
