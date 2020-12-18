package com.easyarch.controller;

import com.easyarch.entity.MyUser;
import com.easyarch.service.PyqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@RequestMapping("/pyq")
@Controller
public class PyqController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    PyqService pyqService;


    @RequestMapping(value="changeHistoryRequest")
    @ResponseBody
    public int changeHistoryRequest(){
        HttpSession session = request.getSession();
        return pyqService.historyRequestCount(((MyUser)session.getAttribute("user")).getSno());
    }
}
