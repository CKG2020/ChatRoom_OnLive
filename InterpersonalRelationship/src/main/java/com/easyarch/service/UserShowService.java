package com.easyarch.service;

import com.easyarch.entity.AnalyzingData;
import com.easyarch.entity.UserShow;
import com.easyarch.mapper.PyqMapper;
import com.easyarch.mapper.UserBoardMapper;
import com.easyarch.mapper.UserMapper;
import com.easyarch.mapper.UserShowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserShowService {
    @Autowired
    UserShowMapper userShowMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    PyqMapper pyqMapper;
    @Autowired
    UserBoardMapper userBoardMapper;


    public List<UserShow> findUsersBySno(String sno) {
        return userShowMapper.findBySno(sno);
    }

    public List<UserShow> findUsersByAge(int age) {
        return userShowMapper.findByAge(age);
    }



    public List<UserShow> findUsersByCollage(String collage) {
        return userShowMapper.findByCollage(collage);
    }

    public List<UserShow> findUsersByClass(String sclass) {
        return userShowMapper.findByClass(sclass);
    }

    public List<UserShow> findUsersByName(String name) {
        return userShowMapper.findByName(name);
    }


    public int findAgeCount(int age){
        return userMapper.findAgeCount(age);
    }
    public int findNameCount(String name) {

        return  userMapper.findNameCount(name);
    }
    public int findCollageCount(String collage) {
        return userMapper.findCollageCount(collage);
    }

    public int findClassCount(String sclass) {
        return userMapper.findClassCount(sclass);
    }

    public int findSnoCount(String sno) {
        return userMapper.findSnoCount(sno);
    }

    public boolean deleteUser(String sno) {
        pyqMapper.delpyq(sno);
        userMapper.delUser(sno);
        userBoardMapper.delb(sno);
        return true;
    }

    public List<Integer> findScore(){
        return  userBoardMapper.findScore();
    }



    public AnalyzingData analyzingRes(){
        AnalyzingData data = new AnalyzingData();

        int finishedCount=findAllFinished();
        int allCount=findallcount();

        data.setAllCount(allCount);
        data.setFinishedCount(finishedCount);

        data.setPercent(percent(finishedCount,allCount));
        List<Integer> list = findScore();
        int countA=0;
        int countB=0;
        int countC=0;
        int countD=0;
        for (Integer integer : list) {
            if (integer >= 48) {
                countA++;
            } else if (integer >= 37) {
                countB++;
            } else if (integer >= 27) {
                countC++;
            } else {
                countD++;
            }
        }
        data.setCountA(countA);
        data.setCountB(countB);
        data.setCountC(countC);
        data.setCountD(countD);
        data.setPercentA(percent(countA,finishedCount));
        data.setPercentB(percent(countB,finishedCount));
        data.setPercentC(percent(countC,finishedCount));
        data.setPercentD(percent(countD,finishedCount));
        return data;
    }
    public double percent(int a,int b){
        return Math.floor(((double) a / (double) b)*100);
    }
    public int findAllFinished(){
        return userBoardMapper.allFinished();
    }

    public int findallcount() {
        return userMapper.findallcount();
    }


//    public int findAllFinished(){
//        return sqlSession.selectOne("UserBoard.allFinished");
//    }
//
//    public int findAgeCount(int age){
//        return sqlSession.selectOne("UserMapper.findAgeCount",age);
//    }

}
