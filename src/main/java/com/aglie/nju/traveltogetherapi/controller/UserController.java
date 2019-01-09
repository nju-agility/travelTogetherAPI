package com.aglie.nju.traveltogetherapi.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aglie.nju.traveltogetherapi.mapper.UserMapper;
import com.aglie.nju.traveltogetherapi.model.ResultModel;
import com.aglie.nju.traveltogetherapi.model.UserInfo;
import com.aglie.nju.traveltogetherapi.util.ResultTools;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
                String jwtToken = Jwts.builder().setSubject(user.getAccount()).
                        claim("roles","member").setIssuedAt(new Date()).
                        signWith(SignatureAlgorithm.HS256, "secretkey").compact();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("token",jwtToken);
//                map.put("content", user);
                return ResultTools.result(0, "", map);
            }else{
                return ResultTools.result(1002, "", null);
            }
        } catch (Exception e) {
            return ResultTools.result(404, e.getMessage(), null);
        }
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public ResultModel addUser(UserInfo user){
        try{
            UserInfo userInfo = userMapper.selectUserByAccount(user.getAccount());
            if (userInfo == null) {
                int code = userMapper.addUser(user);
                if(code == 1) {
                    return ResultTools.result(0, "", null);
                }
                return ResultTools.result(404, "failed", null);
            }
            return ResultTools.result(1003,"",null);
        }catch (Exception e){
            return ResultTools.result(404, e.getMessage(), null);
        }

    }

    @RequestMapping(value = {"/updateUserInfo"}, method = RequestMethod.POST)
    public ResultModel updateUser(UserInfo user){
        try {
            if (user.getName() == null || user.getGender() == null || user.getAge() == null ||
            user.getCity() == null || user.getCode() == null || user.getPasswd() == null || user.getAccount() == null){
                return  ResultTools.result(1001, "", null);
            }
            int code = userMapper.updateUser(user);
            System.out.println(code);
            if(code == 1){
                return ResultTools.result(0, "success", null);
            }
            return ResultTools.result(404,"failed", null);
        }catch (Exception e){
            return ResultTools.result(404, e.getMessage(), null);
        }

    }
}
