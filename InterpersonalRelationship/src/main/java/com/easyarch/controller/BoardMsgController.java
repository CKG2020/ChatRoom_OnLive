package com.easyarch.controller;

import com.easyarch.entity.BoardMsg;
import com.easyarch.entity.MyUser;
import com.easyarch.service.BoardMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/board")
@Controller
public class BoardMsgController {
    @Autowired
    BoardMsgService boardMsgService;
    @Autowired
    HttpServletRequest request;


    @RequestMapping(value = "changeHistoryMsg")
    @ResponseBody
    public int changeHistoryMsg() {
        HttpSession session = request.getSession();
        return boardMsgService.historyMsgCount(((MyUser) session.getAttribute("user")).getSno());
    }


    @RequestMapping(value = "/getBoard")
    @ResponseBody
    public List<BoardMsg> getBoard(){
        HttpSession session = request.getSession();
        return  boardMsgService.showBoardMsg(((MyUser)session.getAttribute("user")).getSno());
    }

}
