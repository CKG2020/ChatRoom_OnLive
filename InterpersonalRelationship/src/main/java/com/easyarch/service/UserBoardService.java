package com.easyarch.service;

import com.easyarch.entity.MyUser;
import com.easyarch.entity.ReturnAnswer;
import com.easyarch.entity.Tips;
import com.easyarch.entity.UserBoard;
import com.easyarch.mapper.UserBoardMapper;
import com.easyarch.util.LoadTxt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//
@Service
public class UserBoardService {
    @Autowired
    private UserBoardMapper userBoardMapper;
//    @Autowired
//    private BoardMsgMapper boardMsgMapper;

    public boolean isFinished(String sno) {
       return   userBoardMapper.isFinished(sno);
    }

//    public UserBoard addScores(String sno, List<ReturnAnswer> list) {
//        int scores = 0;
//        for (ReturnAnswer returnAnswer : list) {
//            scores += returnAnswer.getScore();
//        }
//        return userDao.setScores(sno,scores);
//    }

    public UserBoard setScores(String sno, List<ReturnAnswer> list) {
        int scores = 0;
        for (ReturnAnswer returnAnswer : list) {
            scores += returnAnswer.getScore();
        }

        UserBoard userBoard = new UserBoard();
        userBoard.setSno(sno);
        userBoard.setIsFinishedQuestion(true);
        userBoard.setScores(scores);

        userBoardMapper.updateFinishedQuestion(true,scores,sno);
        return userBoard;
    }

  public String searchSocres(String sno){
      int scores = userBoardMapper.searchScore(sno);
      String x;
      if(scores>=48){
          x="A";
      }else if(scores>=37){
          x="B";
      }else if(scores>=27){
          x="C";
      }else {
          x="D";
      }
      return LoadTxt.sendResult(x);

  }








}







