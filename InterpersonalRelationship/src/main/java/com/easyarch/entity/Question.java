package com.easyarch.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Question implements Serializable {

    private int QuestionID;//题号
    private String Title;
    private List<Answer> answers;
    private boolean isFinished = false;

}
