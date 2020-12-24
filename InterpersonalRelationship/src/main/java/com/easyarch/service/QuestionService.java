package com.easyarch.service;

import com.easyarch.entity.Question;
import com.easyarch.mapper.QuestionMapper;
import com.easyarch.util.LoadTxt;
import com.easyarch.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    public List showQuestion(int cur, int pageSize){
        String s = questionMapper.loadQuestions();
        List<Question> questions = LoadTxt.ReadQuestions(s);
        List list = Page.pageDiv(cur, pageSize, questions);
        return list;
    }

}
