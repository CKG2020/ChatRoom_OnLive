package com.controller;

import com.LoadTxt;
import com.Page;
import com.entity.Question;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



import java.util.List;
//@Controller
public class TestController {
//    @RequestMapping("show")
//    @ResponseBody
//    public List showQuestions(@RequestParam int curr, int pageSize){
//        System.out.println(curr+"\n"+pageSize);
//        String s="classpath:Questions/Research_2";
//        List<Question> questions = LoadTxt.ReadQuestions(s);
//        List list = Page.pageDiv(curr, pageSize, questions);
//        return list;
//    }


    public static void main(String[] args) {
        int curr=1;
        int pageSize=2;
        System.out.println(curr+"\n"+pageSize);

        String s="Questions/Research_2";
        List<Question> questions = LoadTxt.ReadQuestions(s);
        List list = Page.pageDiv(curr, pageSize, questions);

    }
}
