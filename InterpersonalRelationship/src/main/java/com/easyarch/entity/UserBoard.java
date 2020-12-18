package com.easyarch.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserBoard implements Serializable {
    private boolean IsFinishedQuestion ;
    private int Scores;
    private String Sno;
    private String Birth;
}
