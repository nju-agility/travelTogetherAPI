package com.aglie.nju.traveltogetherapi.mapper;

import com.aglie.nju.traveltogetherapi.model.RecordInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RecordMapper {
    /*******查询某用户参加过的活动********/
    @Select("select * from user_records where account = #{account}")
    public List<RecordInfo> selectUserRecords(String account);

    /*******添加用户参加的活动记录********/
    @Select("insert into user_records(account,aid) values (#{account},#{aid})")
    public void insertUserRecords(RecordInfo recordInfo);
}
