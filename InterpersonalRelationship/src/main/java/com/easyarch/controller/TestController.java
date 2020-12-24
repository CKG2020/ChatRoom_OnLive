package com.easyarch.controller;

import com.easyarch.entity.UserShow;
import com.easyarch.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {
    @RequestMapping(value = "/test")
    @ResponseBody
    public  String  test(){
        List<UserShow> sujia = userInfoService.findUsersByName("SUJIA");
        for (UserShow a:sujia
             ) {
            System.out.println(a.toString());

        }
        return "loginAdmin";
    }

    @Autowired
    UserInfoService userInfoService;



}
