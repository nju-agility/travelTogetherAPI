package com.aglie.nju.traveltogetherapi.controller;

import com.aglie.nju.traveltogetherapi.mapper.AdminMapper;
import com.aglie.nju.traveltogetherapi.model.AdminInfo;
import com.aglie.nju.traveltogetherapi.model.ResultModel;
import com.aglie.nju.traveltogetherapi.util.ResultTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AdminController {
    @Autowired
    private AdminMapper adminMapper;

    @RequestMapping(value = {"/adminLogin"}, method = RequestMethod.GET)
    public ResultModel selectAdminByAccount(String accout, String passwd) {

        System.out.println("acc:"+accout+",pass:"+passwd);
        try{
            if(accout == null || passwd == null){
                return ResultTools.result(1001, "", null);
            }
            AdminInfo adminInfo = adminMapper.selectAdminByAccount(accout);
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
}
