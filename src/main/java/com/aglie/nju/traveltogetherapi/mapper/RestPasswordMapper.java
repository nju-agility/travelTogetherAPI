package com.aglie.nju.traveltogetherapi.mapper;

import com.aglie.nju.traveltogetherapi.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RestPasswordMapper {
    /*******重置用户密码********/
    @Update("update users set passwd=#{passwd} where account=#{account}")
    public int restPasswd(UserInfo user);
}
