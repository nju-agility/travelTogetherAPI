package com.aglie.nju.traveltogetherapi.controller;

import com.aglie.nju.traveltogetherapi.mapper.AdminMapper;
import com.aglie.nju.traveltogetherapi.model.*;
import com.aglie.nju.traveltogetherapi.util.ResultTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AdminController {
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 管理员登录
     * @param account
     * @param passwd
     * @return
     */
    @RequestMapping(value = {"/adminLogin"}, method = RequestMethod.GET)
    public ResultModel selectAdminByAccount(String account, String passwd) {

        System.out.println("acc:"+account+",pass:"+passwd);
        try{
            if(account == null || passwd == null){
                return ResultTools.result(1001, "", null);
            }
            AdminInfo adminInfo = adminMapper.selectAdminByAccount(account);
            System.out.println("adminInfo:"+adminInfo.getAccount()+","+adminInfo.getPasswd());
            if(adminInfo == null){
                return ResultTools.result(1002, "", null);
            }
            if(adminInfo.getPasswd().equals(passwd)){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("content", adminInfo);
                return ResultTools.result(0, "", map);
            }else {
                return ResultTools.result(1002, "", null);
            }
        }catch (Exception e){
            return ResultTools.result(404, e.getMessage(), null);
        }
    }

    /**
     * 管理员验证发布或取消一个活动
     * @param activity
     * @return
     */
    @RequestMapping(value = {"/checkActivity"}, method = RequestMethod.POST)
    public ResultModel checkActivity(ActivityInfo activity){
        try {
            if (activity.getAid() == null || activity.getStatus() == null){
                return  ResultTools.result(1001, "", null);
            }
            int code = adminMapper.checkActivity(activity);
            System.out.println(code);
            if(code == 1){
                return ResultTools.result(0, "success", null);
            }
            return ResultTools.result(404,"failed", null);
        }catch (Exception e){
            return ResultTools.result(404, e.getMessage(), null);
        }
    }

    @RequestMapping(value = {"/checkActivities"}, method = RequestMethod.POST)
    public ResultModel checkActivity(@RequestBody ArrayList<ActivityInfo> activities){
        try {
            ActivityInfo activity = null;
            for (int i = 0; i < activities.size(); i++){
                System.out.println("活动"+i);
                Map<String, Object> map = new HashMap<String, Object>();
                activity = activities.get(i);
                if (activity.getAid() == null || activity.getStatus() == null){
                    map.put("content",i+1+"个活动");
                    return  ResultTools.result(1001, "", map);
                }
                int code = adminMapper.checkActivity(activity);
                System.out.println(code);
                if(code != 1){
                    map.put("content",i+1+"个活动");
                    return ResultTools.result(404, "failed", map);
                }else{
                    System.out.println("活动"+i+"成功");
                }
            }
            return ResultTools.result(0,"success", null);
        }catch (Exception e){
            return ResultTools.result(404, e.getMessage(), null);
        }
    }
}
