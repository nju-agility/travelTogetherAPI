package com.aglie.nju.traveltogetherapi.mapper;

import com.aglie.nju.traveltogetherapi.model.RecordInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RecordMapper {
    /*******查询某用户参加过的活动********/
    @Select("select * from user_records where account = #{account}")
    List<RecordInfo> selectUserRecords(String account);

    /*******查询某用户参加的某个活动********/
    @Select("select * from user_records where account = #{account} and aid = #{aid}")
    RecordInfo selectUserRecord(RecordInfo recordInfo);

    /*******添加用户参加的活动记录********/
    @Select("insert into user_records(account,aid) values (#{account},#{aid})")
    void insertUserRecords(RecordInfo recordInfo);

    /*******删除用户活动记录********/
    @Delete("delete from user_records where aid=#{aid} and account=#{account}")
    void deleteUserRecord(RecordInfo recordInfo);

}
