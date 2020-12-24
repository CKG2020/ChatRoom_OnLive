package com.easyarch.entity;

import lombok.Data;

@Data
public class FormValidate {

//        update SSHpro.UserInfo set\n" +
//            "        Sname=#{sname},Sage=#{sage},\n" +
//            "        Scall=#{scall},Collage=#{collage},\n" +
//            "        Major=#{major},Sclass=#{sclass},\n" +
//            "        Gender=#{gender},Birth=#{birth}\n" +
//            "        where Sno=#{sno};")
    private String sno;
    private String sname;
    private int age;
    private String scall;
    private String collage;
    private String major;
    private String sclass;
    private int gender;
    private String  birth;
}
