package com.aglie.nju.traveltogetherapi.mapper;

import com.aglie.nju.traveltogetherapi.model.AdminInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    /*******根据account查询符合管理员********/
    @Select("select * from admins where account = #{account}")
    public AdminInfo selectAdminByAccount(String account);
}
