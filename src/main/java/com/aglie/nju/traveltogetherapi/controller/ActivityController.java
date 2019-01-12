package com.aglie.nju.traveltogetherapi.controller;

import com.aglie.nju.traveltogetherapi.mapper.ActivityMapper;
import com.aglie.nju.traveltogetherapi.model.ActivityInfo;
import com.aglie.nju.traveltogetherapi.model.ResultModel;
import com.aglie.nju.traveltogetherapi.util.ResultTools;
import com.aglie.nju.traveltogetherapi.util.CkeckParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ActivityController {
    @Autowired
    private ActivityMapper activityMapper;

    //查询某用户刚创建待审核的活动
    @RequestMapping(value = {"/userCreatedActivities"}, method = RequestMethod.GET)
    public ResultModel selectUserCreatedActivities(String owner){
        try{
            if (owner == null){
                return ResultTools.result(1001,"",null);
            }
            List<ActivityInfo> activities = activityMapper.selectUserCreatedActivities(owner);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("content", activities);
            return ResultTools.result(0,"",map);
        }catch (Exception e){
            return ResultTools.result(404, e.getMessage(),null);
        }
    }

    //查询某用户创建已结束的活动
    @RequestMapping(value = {"/userFinishedActivities"}, method = RequestMethod.GET)
    public ResultModel selectUserFinishedActivities(String owner){
        try{
            if (owner == null){
                return ResultTools.result(1001,"",null);
            }
            List<ActivityInfo> activities = activityMapper.selectUserFinishedActivities(owner);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("content", activities);
            return ResultTools.result(0,"",map);
        }catch (Exception e){
            return ResultTools.result(404, e.getMessage(),null);
        }
    }

    //查询某用户创建已验证发布的活动
    @RequestMapping(value = {"/userPublishedActivities"}, method = RequestMethod.GET)
    public ResultModel selectUserPublishedActivities(String owner){
        try{
            if (owner == null){
                return ResultTools.result(1001,"",null);
            }
            List<ActivityInfo> activities = activityMapper.selectUserPublishedActivities(owner);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("content", activities);
            return ResultTools.result(0,"",map);
        }catch (Exception e){
            return ResultTools.result(404, e.getMessage(),null);
        }
    }

    //查询某用户被撤销的活动
    @RequestMapping(value = {"/userRepealActivities"}, method = RequestMethod.GET)
    public ResultModel selectUserRepealActivities(String owner){
        try{
            if (owner == null){
                return ResultTools.result(1001,"",null);
            }
            List<ActivityInfo> activities = activityMapper.selectUserRepealActivities(owner);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("content", activities);
            return ResultTools.result(0,"",map);
        }catch (Exception e){
            return ResultTools.result(404, e.getMessage(),null);
        }
    }

    //查询某用户正在参加的活动
    @RequestMapping(value = {"/userAttendActivity"}, method = RequestMethod.GET)
    public ResultModel selectUserAttendActivity(Integer activity_id){
        try{
            if (activity_id == null){
                return ResultTools.result(1001,"",null);
            }
            List<ActivityInfo> activities = activityMapper.selectUserAttendActivity(activity_id);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("content", activities);
            return ResultTools.result(0,"",map);
        }catch (Exception e){
            return ResultTools.result(404, e.getMessage(),null);
        }
    }

    //查询所有已验证发布的活动
    @RequestMapping(value = {"/publishedActivities"}, method = RequestMethod.GET)
    public ResultModel selectPublishedActivities(){
        try{
            List<ActivityInfo> activities = activityMapper.selectPublishedActivities();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("content", activities);
            return ResultTools.result(0,"",map);
        }catch (Exception e){
            return ResultTools.result(404, e.getMessage(),null);
        }
    }

    //查询某种类别可参加的活动
    @RequestMapping(value = {"/typeActivities"}, method = RequestMethod.GET)
    public ResultModel selectTypeActivities(String type){
        try{
            if(type == null){
                return ResultTools.result(1001,"",null);
            }
            List<ActivityInfo> activities = activityMapper.selectTypeActivities(type);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("content", activities);
            return ResultTools.result(0,"",map);
        }catch (Exception e){
            return ResultTools.result(404, e.getMessage(),null);
        }
    }

    //按城市查询可参加的所有活动
    @RequestMapping(value = {"/cityActivities"}, method = RequestMethod.GET)
    public ResultModel selectCityActivities(String city){
        try{
            if(city == null){
                return ResultTools.result(1001,"",null);
            }
            List<ActivityInfo> activities = activityMapper.selectCityActivities(city);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("content", activities);
            return ResultTools.result(0,"",map);
        }catch (Exception e){
            return ResultTools.result(404, e.getMessage(),null);
        }
    }

    //按城市查询可参加的所有活动
    @RequestMapping(value = {"/cityAndTypeActivities"}, method = RequestMethod.GET)
    public ResultModel selectCityActivities(String city, String type){
        try{
            if(city == null || type == null){
                return ResultTools.result(1001,"",null);
            }
            List<ActivityInfo> activities = activityMapper.selectCityAndTypeActivities(city,type);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("content", activities);
            return ResultTools.result(0,"",map);
        }catch (Exception e){
            return ResultTools.result(404, e.getMessage(),null);
        }
    }

    //管理人员查询所有待验证发布的活动
    @RequestMapping(value = {"/toPublishedActivities"}, method = RequestMethod.GET)
    public ResultModel selectToPublishedActivities(){
        try{
            List<ActivityInfo> activities = activityMapper.selectToPublishedActivities();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("content", activities);
            return ResultTools.result(0,"",map);
        }catch (Exception e){
            return ResultTools.result(404, e.getMessage(),null);
        }
    }

    //用户创建活动
    @RequestMapping(value = {"/addActivity"}, method = RequestMethod.POST)
    public ResultModel addActivity(ActivityInfo activity){
        try {
            if (!CkeckParameter.checkActivity(activity)){
                return ResultTools.result(1001,"",null);
            }
            int code = activityMapper.addActivity(activity);
            if(code == 1) {
                return ResultTools.result(0, "", null);
            }
            return ResultTools.result(404, "failed", null);
        }catch (Exception e){
            return ResultTools.result(404,e.getMessage(),null);
        }
    }

}
