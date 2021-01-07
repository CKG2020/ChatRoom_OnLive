package com.easyarch.service;
import com.easyarch.entity.FormValidate;
import com.easyarch.entity.MyUser;
import com.easyarch.entity.UserShow;
import com.easyarch.mapper.UserMapper;
import com.easyarch.mapper.UserShowMapper;
import com.easyarch.util.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class UserInfoService {

    List<UserShow> userList;

    @Autowired
        UserMapper userMapper;
        @Autowired
        UserShowMapper userShowMapper;
        //登录
        public Object loginUser(String Sno, String Spwd) {
//        return loginMapper.loginUser(Sno);

            MyUser myUser = userMapper.loginUser(Sno);
            if (myUser != null) {
                if (Spwd.equals(myUser.getSpwd())) {
                    return myUser;
                } else {
                    return 1;
                }
            } else {
                return 0;
            }

        }

        //按学号查找　　确定是否重复  查询所有内容
        public MyUser findUserBySno(String Sno){

            return userMapper.findUserBySno(Sno);
        }

        //注册添加新用户
        public int addUser(String Sno,String Spwd){
            int a= userMapper.addUser(Sno, Spwd);
            if (a!=0){
                return 1;
            }
            else
                return 0;
        }

        //完善信息


        public int   updateUser(FormValidate  formValidate){
            return   userMapper.updateUser(formValidate.getSname(),formValidate.getAge(),formValidate.getScall(),
                    formValidate.getCollage(),formValidate.getMajor(),formValidate.getSclass(),formValidate.getGender(),formValidate.getBirth(),formValidate.getSno());
        }


//按照名字查找
        public int findNameCount(String name) {
            return userMapper.findNameCount(name);
        }

//按照学号查找
        public int findSnoCount(String sno) {
            return  userMapper.findSnoCount(sno);
        }

    public List<MyUser> showFriends(String sno) {
//        return sqlSession.selectList("UserBoard.myFriends",sno);

        return userMapper.myFriends(sno);
    }

    public List<UserShow> findUsersByName(String name) {
        userList=userShowMapper.findByName(name);
        return userList;
    }
//
//    public List<MyUser> showFriendsRequest(String sno) {
//        return userBoardMapper.(sno);
//    }



    public int findallcount(){
        return  userMapper.findallcount();
    }


    public List findpage(int curr, int pageSize) {

        return Page.pageDiv(curr,pageSize,userList);
    }
}

