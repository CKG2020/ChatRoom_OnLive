package com.easyarch.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class MyUser implements Serializable {

    private static final long serialVersionUID = 735655488285535299L;

    private String sno;
    private String spwd;
    private String sname;
    private int sage;
    private String scall;
    private String collage;
    private String major;
    private String sclass;
    private String birth;
    private int gender;

}
