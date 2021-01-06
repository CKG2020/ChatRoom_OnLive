package com.easyarch.controller;

import com.easyarch.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    UserInfoService userInfoService;


    @RequestMapping("findallcount")
    @ResponseBody
    public int findallcount(){
        return userInfoService.findallcount();
    }






























    @RequestMapping("deleteuser")
    public String deleteuser(){
        return "admin/demodel";
    }
    @RequestMapping("analyzing")
    public String analyzing(){
        return "admin/analyzing";
    }
    @RequestMapping("listener")
    public String listener(){
        return "admin/listener";
    }
    @RequestMapping("addQuestion")
    public String addQuestion(){
        return "admin/addQuestion";
    }

}
