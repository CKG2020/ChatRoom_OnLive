package com.easyarch.controller;

import com.easyarch.entity.FormValidate;
import com.easyarch.entity.MyUser;
import com.easyarch.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    UserInfoService userService;

    @RequestMapping("/loginAdmin")
    public String loginAdmin(){
        return "first/loginAdmin";
    }

    @RequestMapping("/login")
    public String login(){
        HttpSession session = request.getSession();
        if(session.getAttribute("user")!=null){
            session.removeAttribute("user");
            session.removeAttribute("userBoard");
        }
        return "first/login";
    }
    //用户登录
    @RequestMapping(value = "checkUser" ,method = RequestMethod.POST)
    @ResponseBody
    public Object checkUser(@RequestParam String Sno, String Spwd){
        MyUser myUser;
        Object obj =userService.loginUser(Sno,Spwd);
        if (obj.equals(1)||obj.equals(0)){
            return obj.toString();
        }
        HttpSession session = request.getSession();
        myUser =(MyUser)obj;
        session.setAttribute("user", myUser);
        return myUser;
    }

    //    跳转后再把user查出来
    @RequestMapping("search")
    @ResponseBody
    public Object search(){
        HttpSession session = request.getSession();
        System.out.println("-------------search方法---------------");
        System.out.println(session.getAttribute("user"));
        System.out.println("-----------------------------------");
        return session.getAttribute("user");
    }


    @RequestMapping("register")//用户注册
    public String register(){

        return "first/register";
    }


//验证是否重复
    @RequestMapping("isRepeated")
    @ResponseBody
    public String used(@RequestParam String Sno){
        System.out.println(Sno);
        Object obj = userService.findUserBySno(Sno);
        if(obj!=null){
            return "此用户已存在!";
        }else{
            return "ok";
        }

    }

    //添加用户
    @RequestMapping(value = "addUser" )
    @ResponseBody
    public String submit(@RequestParam String Sno, String Spwd){
        int i = userService.addUser(Sno, Spwd);
        if (i!=0) {
            MyUser user = userService.findUserBySno(Sno);

            System.out.println(Sno);
            HttpSession session = request.getSession();

            session.setAttribute("user", user);
            System.out.println("user" + user);
            return "ok";
        }
        return "error";

    }
 //完善信息
    @RequestMapping(value = "perfectInfo")
    @ResponseBody
    public String updateInfo(@RequestBody FormValidate formValidate){
        userService.updateUser(formValidate);
        System.out.println(formValidate.toString());
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.setAttribute("user",userService.findUserBySno(formValidate.getSno()));
        return "ok";
    }


}
