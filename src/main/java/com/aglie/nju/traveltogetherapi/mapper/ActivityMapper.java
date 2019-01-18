package com.aglie.nju.traveltogetherapi.mapper;

import com.aglie.nju.traveltogetherapi.model.ActivityInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ActivityMapper {
    /*******查询某用户刚创建待审核的活动********/
    @Select("select * from activities where status = 0 and owner = #{owner}")
    public List<ActivityInfo> selectUserCreatedActivities(String owner);

    /*******查询某用户创建已验证发布的活动********/
    @Select("select * from activities where status = 1 and owner = #{owner}")
    public List<ActivityInfo> selectUserPublishedActivities(String owner);

    /*******查询某用户创建已结束的活动********/
    @Select("select * from activities where status = 5 and owner = #{owner}")
    public List<ActivityInfo> selectUserFinishedActivities(String owner);

    /*******查询某用户被撤销的活动********/
    @Select("select * from activities where status = 3 and owner = #{owner}")
    public List<ActivityInfo> selectUserRepealActivities(String owner);

    /*******查询某用户正在参加的活动********/
    @Select("select * from activities where status in (4, 1, 0) and aid = #{activity_id}")
    public List<ActivityInfo> selectUserAttendActivity(Integer activity_id);

    /*******查询某用户参加过的活动********/
    @Select("select * from activities where status in (0,1,3,4,5) and aid = #{activity_id}")
    public ActivityInfo selectAttendedActivity(Integer activity_id);

    /*******查询所有已验证发布的活动********/
    @Select("select * from activities where status = 1")
    public List<ActivityInfo> selectPublishedActivities();

    /*******查询某种类别的活动********/
    @Select("select * from activities where type = #{type} and status = 1")
    public List<ActivityInfo> selectTypeActivities(String type);

    /*******查询某个城市的活动********/
    @Select("select * from activities where city = #{city} and status = 1")
    public List<ActivityInfo> selectCityActivities(String city);

    /*******查询某个城市某个类别的活动********/
    @Select("select * from activities where city = #{city} and status = 1 and type = #{type}")
    public List<ActivityInfo> selectCityAndTypeActivities(String city, String type);

    /*******管理人员查询所有待验证发布的活动********/
    @Select("select * from activities where status = 0")
    public List<ActivityInfo> selectToPublishedActivities();

    /*******用户创建活动********/
    @Insert("insert into activities(status,owner,city,location,title,details,time_start,time_end,type)" +
            " values (0,#{owner},#{city},#{location},#{title},#{details},#{time_start},#{time_end},#{type})")
    public int addActivity(ActivityInfo activity);

    /*******查询用户正在创建的活动********/
    @Select("select * from activities where owner = #{owner} and time_start = #{time_start}")
    public ActivityInfo selectActivity(String owner, String time_start);
}
