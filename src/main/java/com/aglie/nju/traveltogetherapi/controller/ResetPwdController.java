package com.aglie.nju.traveltogetherapi.controller;

import com.aglie.nju.traveltogetherapi.mapper.RestPasswordMapper;
import com.aglie.nju.traveltogetherapi.mapper.UserMapper;
import com.aglie.nju.traveltogetherapi.model.ResultModel;
import com.aglie.nju.traveltogetherapi.model.UserInfo;
import com.aglie.nju.traveltogetherapi.util.RandomPassword;
import com.aglie.nju.traveltogetherapi.util.ResultTools;
import com.aglie.nju.traveltogetherapi.util.javaMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ResetPwdController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RestPasswordMapper restPasswordMapper;

    @RequestMapping(value = {"/resetPassword"}, method = RequestMethod.GET)
    public ResultModel resetPassword(String account, String name){
        try{
            if(account == null || name == null){
                System.out.println("missing para");
                return ResultTools.result(1001, "", null);
            }else{
                String newPassword = RandomPassword.createRandomPassword(8);
                System.out.println("newPassword:"+newPassword);
                UserInfo user = userMapper.selectUserByAccount(account);
                user.setPasswd(newPassword);
                int resultCode = restPasswordMapper.restPasswd(user);
                if (resultCode == 1){
                    javaMail.sendEmail(account, user.getName(), newPassword);
                    return ResultTools.result(0, "", null);
                }else{
                    return ResultTools.result(404, "failed", null);
                }
            }
        }catch (Exception e){
            return ResultTools.result(404, e.getMessage(), null);
        }
    }
}
