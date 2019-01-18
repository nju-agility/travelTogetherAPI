package com.aglie.nju.traveltogetherapi.controller;


import com.aglie.nju.traveltogetherapi.mapper.ActivityMapper;
import com.aglie.nju.traveltogetherapi.mapper.RecordMapper;
import com.aglie.nju.traveltogetherapi.model.ActivityInfo;
import com.aglie.nju.traveltogetherapi.model.RecordInfo;
import com.aglie.nju.traveltogetherapi.model.ResultModel;
import com.aglie.nju.traveltogetherapi.util.FileTools;
import com.aglie.nju.traveltogetherapi.util.ResultTools;
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
public class RecordController {
    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @RequestMapping(value = {"/getRecord"}, method = RequestMethod.GET)
    public ResultModel getRecord(String account){
        try{
            if(account == null){
                return ResultTools.result(1001,"",null);
            }
            List<RecordInfo> records = recordMapper.selectUserRecords(account);
            List<ActivityInfo> activities = new ArrayList<>();
            for (RecordInfo record : records){
                ActivityInfo activityInfo = activityMapper.selectAttendedActivity(record.getAid());
                activityInfo.setActivityURL("/image/3_"+activityInfo.getAid().toString()+".jpg");
//                activityInfo.setActivityURL("/image/" + FileTools.getImg(activityInfo.getAid().toString(),3));
                if(activityInfo != null){
                    activities.add(activityInfo);
                }
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("content", activities);
            return ResultTools.result(0,"",map);
        }catch (Exception e){
            return ResultTools.result(404,e.getMessage(),null);
        }
    }
}
