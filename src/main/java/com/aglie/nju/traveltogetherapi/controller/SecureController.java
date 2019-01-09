package com.aglie.nju.traveltogetherapi.controller;

import com.aglie.nju.traveltogetherapi.model.ResultModel;
import com.aglie.nju.traveltogetherapi.util.ResultTools;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SecureController {
    @RequestMapping(value = {"/secure/login"}, method = RequestMethod.GET)
    public ResultModel loginSuccess() {
//        System.out.println("Token: success!");
        return ResultTools.result(0,"",null);
    }
}
