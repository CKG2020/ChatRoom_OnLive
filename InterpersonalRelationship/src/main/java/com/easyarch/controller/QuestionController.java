package com.easyarch.controller;

import com.easyarch.entity.MyUser;
import com.easyarch.entity.ReturnAnswer;
import com.easyarch.service.QuestionService;
import com.easyarch.service.UserBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/questions")
@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @Autowired
    UserBoardService userBoardService;

    @Autowired
    HttpServletRequest request;

    @RequestMapping("isFinished")
    @ResponseBody
    public String isFinished(@RequestParam String sno) {
        System.out.println(sno);
        if (userBoardService.isFinished(sno)) {
            return "ok";
        }
        return "no";
    }


    @RequestMapping("show")
    @ResponseBody
    public List showQuestions(@RequestParam int curr,int pageSize){
        System.out.println(curr+"\n"+pageSize);
//        questionService.showQuestion();
        return questionService.showQuestion(curr,pageSize);
    }

    @RequestMapping("sendAnswers")
    @ResponseBody
    public String Answers(@RequestBody List<ReturnAnswer> answersList) {
        String sno = ((MyUser)request.getSession().getAttribute("user")).getSno();
        HttpSession session = request.getSession();
        session.setAttribute("userBoard",userBoardService.setScores(sno,answersList));
        System.out.println(session.getAttribute("userBoard"));
        return "";
    }

    @RequestMapping("getResult")
    @ResponseBody
    public String getResult(@RequestParam String sno){
        return userBoardService.searchSocres(sno);
    }



//    @RequestMapping(value="changeHistoryMsg")
//    @ResponseBody
//    public int changeHistoryMsg(){
//        HttpSession session = request.getSession();
//        return userService.historyMsgCount(((User)session.getAttribute("user")).getSno());
//    }


}
