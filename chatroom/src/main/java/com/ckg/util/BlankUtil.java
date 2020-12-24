package com.ckg.util;

public class BlankUtil {
    //判断字符串是否为空
    public static  boolean isBlank(final String   str){
        return (str==null)||(str.trim().length()<=0);
    }

    //判断字符是否为空
    public   static   boolean  idBlank(final  Character cha){

        return (cha==null)||cha.equals(' ');
    }





}
