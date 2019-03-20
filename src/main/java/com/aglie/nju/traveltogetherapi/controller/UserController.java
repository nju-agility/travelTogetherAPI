package com.aglie.nju.traveltogetherapi.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.aglie.nju.traveltogetherapi.mapper.RecordMapper;
import com.aglie.nju.traveltogetherapi.mapper.UserMapper;
import com.aglie.nju.traveltogetherapi.model.RecordInfo;
import com.aglie.nju.traveltogetherapi.model.ResultModel;
import com.aglie.nju.traveltogetherapi.model.UserInfo;
import com.aglie.nju.traveltogetherapi.util.FileTools;
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

    @Autowired
    private RecordMapper recordMapper;

    /**
     * 用户在无Token情况下登录
     * @param account
     * @param passwd
     * @return
     */
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
                map.put("account",user.getAccount());
                map.put("name",user.getName());
                map.put("token",jwtToken);
//                map.put("headURL","/image/" + FileTools.getImg(user.getAccount(),0));
                user.setHeadURL("/image/0_"+user.getAccount()+".jpg");
                user.setStudentPicURL("/image/1_"+user.getAccount()+".jpg");
//                map.put("content", user);
                return ResultTools.result(0, "", map);
            }else{
                return ResultTools.result(1002, "", null);
            }
        } catch (Exception e) {
            return ResultTools.result(404, e.getMessage(), null);
        }
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
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

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = {"/updateUserInfo"}, method = RequestMethod.POST)
    public ResultModel updateUser(UserInfo user){
        try {
            if (user.getName() == null || user.getGender() == null || user.getAge() == null ||
                user.getCity() == null || user.getCode() == null || user.getPasswd() == null ||
                    user.getAccount() == null || user.getSchool() == null){
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

    /**
     * 用户申请参加活动
     * @param user
     * @return
     */
    @RequestMapping(value = {"/userApplyActivity"}, method = RequestMethod.GET)
    public ResultModel userAttendActivity(UserInfo user){
        try {
            if (user.getAccount() ==null || user.getActivity_id() == null){
                return  ResultTools.result(1001, "", null);
            }
            UserInfo userInfo = userMapper.selectUserByAccount(user.getAccount());
            if(userInfo.getActivity_id() != 0 ){
                return ResultTools.result(1003,"",null);
            }
            int code = userMapper.userAttendActivity(user);
            System.out.println(code);
            if(code == 1){
                RecordInfo recordInfo = new RecordInfo();
                recordInfo.setAccount(user.getAccount());
                recordInfo.setAid(user.getActivity_id());
                try {
                    recordMapper.insertUserRecords(recordInfo);
                }catch (Exception e){
                    return ResultTools.result(1003,"",null);
                }
                return ResultTools.result(0, "success", null);
            }
            return ResultTools.result(404,"failed", null);
        }catch (Exception e){
            return ResultTools.result(404, e.getMessage(), null);
        }
    }

    /**
     * 用户退出活动活动
     * @param user
     * @return
     */
    @RequestMapping(value = {"/userQuitActivity"}, method = RequestMethod.GET)
    public ResultModel userQuitActivity(UserInfo user){
        try {
            if (user.getAccount() ==null){
                return  ResultTools.result(1001, "", null);
            }
            UserInfo userInfo = userMapper.selectUserByAccount(user.getAccount());
            Integer aid = userInfo.getActivity_id();
            RecordInfo recordInfo = new RecordInfo();
            recordInfo.setAid(aid);
            recordInfo.setAccount(user.getAccount());
            recordInfo = recordMapper.selectUserRecord(recordInfo);
            recordMapper.deleteUserRecord(recordInfo);
            user.setActivity_id(0);
            int code = userMapper.userQuitActivity(user);
            System.out.println(code);
            if(code == 1){
                return ResultTools.result(0, "success", null);
            }
            return ResultTools.result(404,"failed", null);
        }catch (Exception e){
            return ResultTools.result(404, e.getMessage(), null);
        }
    }


    /**
     * 查看用户信息
     * @param account
     * @return
     */
    @RequestMapping(value = {"/userInfo"}, method = RequestMethod.GET)
    public ResultModel selectUesrByAccount(String account){
        try {
            if (account == null) {
                return ResultTools.result(1001, "", null);
            }
            UserInfo user = userMapper.selectUserByAccount(account);
            if (null == user) {
                return ResultTools.result(1002, "", null);
            }
            Map<String, Object> map = new HashMap<String, Object>();
//            user.setPasswd("****");
//            user.setHeadURL("/image/" + FileTools.getImg(user.getAccount(),0));
            user.setHeadURL("/image/0_"+user.getAccount()+".jpg");
            user.setStudentPicURL("/image/1_"+user.getAccount()+".jpg");
            map.put("content", user);
            return ResultTools.result(0, "", map);
        } catch (Exception e) {
            return ResultTools.result(404, e.getMessage(), null);
        }
    }
}
