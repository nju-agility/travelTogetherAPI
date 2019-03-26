package com.aglie.nju.traveltogetherapi.mapper;

import com.aglie.nju.traveltogetherapi.model.TravelNoteInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TravelNoteMapper {
    /*******用户创建游记********/
    @Insert("insert into travel_notes(account, city, location, title, details, submission_date)"
            + "values (#{account},#{city},#{location},#{title},#{details},#{submission_date})")
    int createTravelNote(TravelNoteInfo noteInfo);

    /*******查看某用户创建的游记********/
    @Select("select * from travel_notes where account=#{account} order by id")
    List<TravelNoteInfo> selectTravelNote(String account);

    /*******查看所有的游记********/
    @Select("select * from travel_notes")
    List<TravelNoteInfo> selectAllTravelNote();

}
