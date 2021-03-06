package com.aglie.nju.traveltogetherapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/manage")
    public String manage() {
        return "manage.html";
    }

    @RequestMapping("/verifying")
    public String verifying() {
        return "verifying.html";
    }

    @RequestMapping("/create")
    public String create() {return "create.html";}

    @RequestMapping("/viewuser")
    public String viewuser() {return "viewuser.html";}


}
