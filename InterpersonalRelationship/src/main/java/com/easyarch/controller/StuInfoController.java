package com.easyarch.controller;

import com.easyarch.entity.BoardMsg;
import com.easyarch.entity.FormValidate;
import com.easyarch.entity.MyUser;
import com.easyarch.entity.UserShow;
import com.easyarch.mapper.UserMapper;
import com.easyarch.service.BoardMsgService;
import com.easyarch.service.PyqService;
import com.easyarch.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class StuInfoController {

    @Autowired
    HttpServletRequest request;
    @Autowired
    BoardMsgService boardMsgService;
   @Autowired
    UserInfoService userInfoService;
   @Autowired
    PyqService pyqService;



    @RequestMapping(value="newMsgCount")
    @ResponseBody
    public int newMsgCount(){
        HttpSession session = request.getSession();
        return boardMsgService.tipMsgCount(((MyUser)session.getAttribute("user")).getSno());
    }


    @RequestMapping(value="newRequestCount")
    @ResponseBody
    public int newRequestCount(){
        HttpSession session = request.getSession();
        System.out.println("这是个什么问题？？？？？？？？？？？？");
//        boardMsgService.tipMsgCount()
        Object testuser = session.getAttribute("user");
        MyUser myUser=(MyUser)testuser;
        System.out.println(myUser.toString());
        int num = boardMsgService.tipRequestCount(((MyUser) session.getAttribute("user")).getSno());
        System.out.println(num);
        return boardMsgService.tipRequestCount(((MyUser)session.getAttribute("user")).getSno());
    }


    @RequestMapping(value = "update")
    @ResponseBody
    public String updateInfo(@RequestBody FormValidate formValidate){
//        userService.updateUser(formValidate);
        boardMsgService.updateUser(formValidate);
        System.out.println(formValidate.toString());
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.setAttribute("user",boardMsgService.findUserBySno(formValidate.getSno()));
        return "ok";
    }




    @RequestMapping(value = "moreInfo")
    public String moreInfo(){

        return "/stu/moreInfo";
    }

//聊天
    @RequestMapping(value = "chat")
    public String ChatRoom(){
        HttpSession session = request.getSession();
        MyUser user = (MyUser) session.getAttribute("user");
        System.out.println(user.getSname());
        return "test/chat";
    }


//问题
    @RequestMapping(value = "questions")
    public String Questions(){
        return "stu/questions";
    }



    @RequestMapping(value = "analyzing")
    public String Analyzing(){
//        userService.tipMsgCount()
        return "stu/analyzing";
    }

    @RequestMapping(value = "board")
    public String Board(){
        boardMsgService.setTimes(0);
        return "stu/board";
    }
    @RequestMapping(value = "friendList")
    public String FriendList(){

        return "stu/friendList";
    }

    @RequestMapping(value="findNameCount")
    @ResponseBody
    public int findNameCount(@RequestParam String Sname){
        return userInfoService.findNameCount(Sname);
    }

    @RequestMapping(value="findSnoCount")
    @ResponseBody
    public int findSnoCount(@RequestParam String Sno){
        return userInfoService.findSnoCount(Sno);
    }
//好友数量
    @RequestMapping(value="friendsCount")
    @ResponseBody
    public int friendsCount(){
        HttpSession session = request.getSession();
        return pyqService.findFriendsCount(((MyUser)session.getAttribute("user")).getSno());
    }
    @RequestMapping(value="FriendList/showFriends")
    @ResponseBody
    public List<MyUser> getFriends(){
        HttpSession session = request.getSession();
        System.out.println(((MyUser)session.getAttribute("user")).getSno());
        System.out.println(userInfoService.showFriends(((MyUser)session.getAttribute("user")).getSno()));
        return userInfoService.showFriends(((MyUser)session.getAttribute("user")).getSno());
    }




    @RequestMapping(value="delFriend")
    @ResponseBody
    public String delFriend(@RequestParam String sno){
        HttpSession session = request.getSession();
        pyqService.delFriend(((MyUser)session.getAttribute("user")).getSno(),sno);
        return "";
    }


    @RequestMapping(value="acceptRequest")
    @ResponseBody
    public String acceptRequest(@RequestParam String sno){
        HttpSession session = request.getSession();
        pyqService.acceptRequest(sno,((MyUser)session.getAttribute("user")).getSno());
        return "";
    }


    @RequestMapping(value="refuseRequest")
    @ResponseBody
    public String refuseRequest(@RequestParam String sno){
        HttpSession session = request.getSession();
        pyqService.refuseRequest(sno,((MyUser)session.getAttribute("user")).getSno());
        return "";
    }



    @RequestMapping(value="addRequest")
    @ResponseBody
    public String addRequest(@RequestParam String sno){
        HttpSession session = request.getSession();
        pyqService.addRequest(((MyUser)session.getAttribute("user")).getSno(),sno);
        return "";
    }


    //此处可能有问题　就是关于数据库收集的ＭｙＵｓｅｒ　是直接返回ｌｉｓｔ呢还是　还是说按照我下面这样式的呢？？？？有待考证
    @RequestMapping(value="findBySno")
    @ResponseBody
    public List<UserShow> findBySno(@RequestParam String Sno){
        System.out.println("Sno"+Sno);
        return (List<UserShow>) userInfoService.findUserBySno(Sno);
    }



    @RequestMapping(value="findByName")
    @ResponseBody
    public List<UserShow> findbyName(@RequestParam String Sname){

        return userInfoService.findUsersByName(Sname);
    }


    @RequestMapping(value="countRequest")
    @ResponseBody
    public int countRequest(){
        HttpSession session = request.getSession();
        return pyqService.countRequest(((MyUser)session.getAttribute("user")).getSno());
    }

//
//    @RequestMapping(value="friendList/getRequest")
//    @ResponseBody
//    public  List<MyUser>  getRequest(){
//        HttpSession session = request.getSession();
//        System.out.println(userService.showFriendsRequest(((MyUser)session.getAttribute("user")).getSno()));
//        return userService.showFriendsRequest(((MyUser)session.getAttribute("user")).getSno());
//    }


}
