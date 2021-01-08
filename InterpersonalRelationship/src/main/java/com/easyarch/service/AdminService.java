package com.easyarch.service;

import com.easyarch.entity.Admin;
import com.easyarch.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

@Autowired
AdminMapper adminMapper;

    public Admin adminlogin(String username, String pwd) {

        Admin user=adminMapper.selectAdmin(username);
        if(user!=null){
            if(pwd.equals(user.getPassword())){
                return user;
            }
        }
        return null;
    }


}
