package com.easyarch.controller;

import com.easyarch.entity.UserShow;
import com.easyarch.service.UserInfoService;
import com.easyarch.service.UserShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    UserShowService  userShowService;


    @RequestMapping("findallcount")
    @ResponseBody
    public int findallcount(){
        return userInfoService.findallcount();
    }


    @RequestMapping("findpage")
    @ResponseBody
    public List<UserShow> findpage(@RequestParam String curr, String pageSize){
        return userInfoService.findpage(Integer.valueOf(curr),Integer.valueOf(pageSize));
    }

    @RequestMapping("findbySno")
    @ResponseBody
    public List<UserShow> findbySno(@RequestParam String Sno){
        return userShowService.findUsersBySno(Sno);
    }

    @RequestMapping("findbyAge")
    @ResponseBody
    public List<UserShow> findbyAge(@RequestParam String Sage){
        return userShowService.findUsersByAge(Integer.valueOf(Sage));
    }

    @RequestMapping("findbyCollage")
    @ResponseBody
    public List<UserShow> findbyCollage(@RequestParam String Collage){
        return userShowService.findUsersByCollage(Collage);
    }


    @RequestMapping("findbyClass")
    @ResponseBody
    public List<UserShow> findbyClass(@RequestParam String Sclass){
        return userShowService.findUsersByClass(Sclass);
    }

    @RequestMapping("findbyName")
    @ResponseBody
    public List<UserShow> findbyName(@RequestParam String Sname){
        return userShowService.findUsersByName(Sname);
    }


    @RequestMapping("findAgeCount")
    @ResponseBody
    public int findAgeCount(@RequestParam String age){
        return userShowService.findAgeCount(Integer.valueOf(age));
    }

    @RequestMapping("findNameCount")
    @ResponseBody
    public int findNameCount(@RequestParam String Sname){
        return userShowService.findNameCount(Sname);
    }

    @RequestMapping("findCollageCount")
    @ResponseBody
    public int findCollageCount(@RequestParam String Collage){
        return userShowService.findCollageCount(Collage);
    }


    @RequestMapping("findClassCount")
    @ResponseBody
    public int findClassCount(@RequestParam String Sclass){
        return userShowService.findClassCount(Sclass);
    }

    @RequestMapping("findSnoCount")
    @ResponseBody
    public int findSnoCount(@RequestParam String Sno){
        return userShowService.findSnoCount(Sno);
    }












//=========================================================================
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
//========================================================================
@RequestMapping("delete")
@ResponseBody
public String delete(@RequestParam String Sno){
    if(userShowService.deleteUser(Sno))
        return "ok";
    else{
        return "wrong!";
    }
}

}
