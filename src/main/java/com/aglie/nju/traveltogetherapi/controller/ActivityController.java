package com.aglie.nju.traveltogetherapi.controller;

import com.aglie.nju.traveltogetherapi.mapper.ActivityMapper;
import com.aglie.nju.traveltogetherapi.mapper.RecordMapper;
import com.aglie.nju.traveltogetherapi.mapper.UserMapper;
import com.aglie.nju.traveltogetherapi.model.ActivityInfo;
import com.aglie.nju.traveltogetherapi.model.RecordInfo;
import com.aglie.nju.traveltogetherapi.model.ResultModel;
import com.aglie.nju.traveltogetherapi.model.UserInfo;
import com.aglie.nju.traveltogetherapi.util.ResultTools;
import com.aglie.nju.traveltogetherapi.util.CkeckParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ActivityController {
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RecordMapper recordMapper;

    //查询某用户刚创建待审核的活动
    @RequestMapping(value = {"/userCreatedActivities"}, method = RequestMethod.GET)
    public ResultModel selectUserCreatedActivities(String owner){
        try{
            if (owner == null){
                return ResultTools.result(1001,"",null);
            }
            List<ActivityInfo> activities = activityMapper.selectUserCreatedActivities(owner);
            for (ActivityInfo activity : activities){
                activity.setActivityURL("/image/3_"+activity.getAid().toString()+".jpg");
//                activity.setActivityURL("/image/" + FileTools.getImg(activity.getAid().toString(),3));
            }
            Map<String, Object> map = new HashMap<>();
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
            for (ActivityInfo activity : activities){
//                activity.setActivityURL("/image/" + FileTools.getImg(activity.getAid().toString(),3));
                activity.setActivityURL("/image/3_"+activity.getAid().toString()+".jpg");
            }
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
            for (ActivityInfo activity : activities){
//                activity.setActivityURL("/image/" + FileTools.getImg(activity.getAid().toString(),3));
                activity.setActivityURL("/image/3_"+activity.getAid().toString()+".jpg");
            }
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
            for (ActivityInfo activity : activities){
//                activity.setActivityURL("/image/" + FileTools.getImg(activity.getAid().toString(),3));
                activity.setActivityURL("/image/3_"+activity.getAid().toString()+".jpg");
            }
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
            for (ActivityInfo activity : activities){
//                activity.setActivityURL("/image/" + FileTools.getImg(activity.getAid().toString(),3));
                activity.setActivityURL("/image/3_"+activity.getAid().toString()+".jpg");
            }
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
            List<ActivityInfo> acs = new ArrayList<ActivityInfo>();
            for (ActivityInfo activity : activities){
//                activity.setActivityURL("/image/" + FileTools.getImg(activity.getAid().toString(),3));
                activity.setActivityURL("/image/3_"+activity.getAid().toString()+".jpg");
                acs.add(activity);
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("content", acs);
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
            for (ActivityInfo activity : activities){
//                activity.setActivityURL("/image/" + FileTools.getImg(activity.getAid().toString(),3));
                activity.setActivityURL("/image/3_"+activity.getAid().toString()+".jpg");
            }
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
            for (ActivityInfo activity : activities){
//                activity.setActivityURL("/image/" + FileTools.getImg(activity.getAid().toString(),3));
                activity.setActivityURL("/image/3_"+activity.getAid().toString()+".jpg");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("content", activities);
            return ResultTools.result(0,"",map);
        }catch (Exception e){
            return ResultTools.result(404, e.getMessage(),null);
        }
    }

    //按城市查询某种可参加的所有活动
    @RequestMapping(value = {"/cityAndTypeActivities"}, method = RequestMethod.GET)
    public ResultModel selectCityActivities(String city, String type){
        try{
            if(city == null || type == null){
                return ResultTools.result(1001,"",null);
            }
            List<ActivityInfo> activities = activityMapper.selectCityAndTypeActivities(city,type);
            for (ActivityInfo activity : activities){
//                activity.setActivityURL("/image/" + FileTools.getImg(activity.getAid().toString(),3));
                activity.setActivityURL("/image/3_"+activity.getAid().toString()+".jpg");
            }
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
            for (ActivityInfo activity : activities){
//                activity.setActivityURL("/image/" + FileTools.getImg(activity.getAid().toString(),3));
                activity.setActivityURL("/image/3_"+activity.getAid().toString()+".jpg");
            }
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
            ActivityInfo ac = activityMapper.selectActivity(activity.getOwner(), activity.getTime_start());
            if (ac != null){
                return ResultTools.result(1003,"",null);
            }
            int code = activityMapper.addActivity(activity);
            if(code == 1) {
                ActivityInfo activityInfo = activityMapper.selectActivity(activity.getOwner(),activity.getTime_start());
                System.out.println(activityInfo.getAid());
                UserInfo user = userMapper.selectUserByAccount(activityInfo.getOwner());
                user.setActivity_id(activityInfo.getAid());
                System.out.println(user.getActivity_id());
                int res = userMapper.updateUserActivity(user);
                System.out.println(res);
                if(res == 1){
                    RecordInfo recordInfo = new RecordInfo();
                    recordInfo.setAccount(user.getAccount());
                    recordInfo.setAid(user.getActivity_id());
                    recordMapper.insertUserRecords(recordInfo);
                }
                List<ActivityInfo> activities = activityMapper.selectUserCreatedActivities(activity.getOwner());
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("content", activities);
                return ResultTools.result(0, "", map);
            }
            return ResultTools.result(404, "failed", null);
        }catch (Exception e){
            return ResultTools.result(404,e.getMessage(),null);
        }
    }

}
