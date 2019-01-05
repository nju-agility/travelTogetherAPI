package com.aglie.nju.traveltogetherapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aglie.nju.traveltogetherapi.mapper.UserMapper;
import com.aglie.nju.traveltogetherapi.model.ResultModel;
import com.aglie.nju.traveltogetherapi.model.UserInfo;
import com.aglie.nju.traveltogetherapi.util.ResultTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ResultModel selectUesrByAccount(String account, String passwd){
        try {
            if (account == null || passwd == null) {
                return ResultTools.result(1001, "", null);
            }
            UserInfo user = userMapper.selectUserByAccount(account);
            if (null == user) {
                return ResultTools.result(1002, "", null);
            }
            if (user.getPasswd().equals(passwd)) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("content", user);
                return ResultTools.result(0, "", map);
            }else{
                return ResultTools.result(1002, "", null);
            }
        } catch (Exception e) {
            return ResultTools.result(404, e.getMessage(), null);
        }
    }
}
