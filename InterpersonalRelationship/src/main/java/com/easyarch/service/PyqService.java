package com.easyarch.service;

import com.easyarch.entity.FriendRequest;
import com.easyarch.entity.Tips;
import com.easyarch.mapper.PyqMapper;
import com.easyarch.mapper.UserBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PyqService {
     private int countRequest=0;

    @Autowired
    PyqMapper pyqMapper;
    @Autowired
    UserBoardMapper userBoardMapper;

    public int historyRequestCount(String sno) {
        countRequest=pyqMapper.countRequest(sno);
        System.out.println(countRequest);
        Tips tips = new Tips();
        tips.setSno(sno);
        tips.setCount(countRequest);
        userBoardMapper.setHistoryRequestCount(tips.getCount(),tips.getSno());
        return 0;
    }

//查找好友的数量
    public int findFriendsCount(String sno) {

       return pyqMapper.countFriends(sno);
    }



    public boolean delFriend(String sno1, String sno2) {

        pyqMapper.delFriend1(sno1,sno2);
        pyqMapper.delFriend2(sno1,sno2);
        return true;
    }

    public boolean acceptRequest(String sno1, String sno2) {

        pyqMapper.acceptAdd(sno1, sno2);
        pyqMapper.addFriend(sno1, sno2);


        return true;
    }


    public boolean refuseRequest(String sno1, String sno2) {
//        FriendRequest request = new FriendRequest();
//        request.setSno1(sno1);
//        request.setSno2(sno2);
//        request.setAddStatus(false);
//        userDao.refuseRequest(request);
        pyqMapper.refuseAdd(sno1,sno2);
        return true;
    }

    public boolean addRequest(String sno1, String sno2) {
//        FriendRequest request = new FriendRequest();
//        request.setSno1(sno1);
//        request.setSno2(sno2);
//        request.setAddStatus(false);
//        userDao.addRequest(request);
        pyqMapper.addFriendRequest(sno1, sno2);
        return true;
    }


    public int countRequest(String sno) {
        return  pyqMapper.countRequest(sno);
//        return sqlSession.selectOne("UserBoard.countRequest",sno);
    }
}
