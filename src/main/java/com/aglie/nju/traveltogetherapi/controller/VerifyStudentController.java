package com.aglie.nju.traveltogetherapi.controller;

import com.aglie.nju.traveltogetherapi.mapper.UserMapper;
import com.aglie.nju.traveltogetherapi.mapper.VerifyStudentMapper;
import com.aglie.nju.traveltogetherapi.model.ResultModel;
import com.aglie.nju.traveltogetherapi.model.UserInfo;
import com.aglie.nju.traveltogetherapi.util.ResultTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class VerifyStudentController {

   @Autowired
   private VerifyStudentMapper verifyStudentMapper;

   @Autowired
   private UserMapper userMapper;

    /**
     * 管理员查询所有未认证用户
     */
   @RequestMapping(value = "/queryStudent", method = RequestMethod.GET)
    public ResultModel queryStudent(){
        try{
            List<UserInfo> users = verifyStudentMapper.selectUserByStatus();
            for(UserInfo user:users){
                user.setHeadURL("/image/0_"+user.getAccount()+".jpg");
                user.setStudentPicURL("/image/1_"+user.getAccount()+".jpg");
                System.out.println(user.getAccount());

            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("content", users);
            return ResultTools.result(0,"",map);
        }catch (Exception e){
            return ResultTools.result(404, e.getMessage(),null);
        }
   }

    /**
     * 管理员审核学生身份
     */
    @RequestMapping(value = "/verifyStudent", method = RequestMethod.GET)
    public ResultModel verifyStudent(String account, int status){
        UserInfo user = userMapper.selectUserByAccount(account);
        user.setStatus(status);
        int resCode = verifyStudentMapper.changeStudentStatus(user);
        if(resCode == 1){
            return ResultTools.result(0, "success", null);
        }
        return ResultTools.result(404,"failed", null);
    }
}
