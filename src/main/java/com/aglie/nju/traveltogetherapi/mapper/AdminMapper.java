package com.aglie.nju.traveltogetherapi.mapper;

import com.aglie.nju.traveltogetherapi.model.ActivityInfo;
import com.aglie.nju.traveltogetherapi.model.AdminInfo;
import com.aglie.nju.traveltogetherapi.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminMapper {
    /*******根据account查询符合管理员********/
    @Select("select * from admins where account = #{account}")
    public AdminInfo selectAdminByAccount(String account);

    /*******管理员验证活动********/
    @Update("update activities set status=#{status} where aid=#{aid}")
    public int checkActivity(ActivityInfo activity);
}
