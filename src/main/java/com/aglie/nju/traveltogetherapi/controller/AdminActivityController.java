package com.aglie.nju.traveltogetherapi.controller;

import com.aglie.nju.traveltogetherapi.mapper.ActivityMapper;
import com.aglie.nju.traveltogetherapi.model.ActivityInfo;
import com.aglie.nju.traveltogetherapi.model.RecordInfo;
import com.aglie.nju.traveltogetherapi.model.ResultModel;
import com.aglie.nju.traveltogetherapi.model.UserInfo;
import com.aglie.nju.traveltogetherapi.util.CkeckParameter;
import com.aglie.nju.traveltogetherapi.util.ResultTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AdminActivityController {

    @Autowired
    private ActivityMapper activityMapper;

    //用户创建活动
    @RequestMapping(value = {"/adminAddActivity"}, method = RequestMethod.POST)
    public ResultModel adminAddActivity(ActivityInfo activity){
        try {
            if (!CkeckParameter.checkActivity(activity)){
                return ResultTools.result(1001,"",null);
            }
            int code = activityMapper.adminAddActivity(activity);
            if(code == 1) {
                return ResultTools.result(0, "", null);
            }
            return ResultTools.result(404, "failed", null);
        }catch (Exception e){
            return ResultTools.result(404,e.getMessage(),null);
        }
    }
}
