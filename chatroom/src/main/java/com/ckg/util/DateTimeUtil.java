package com.ckg.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateTimeUtil {
    private  static  final Logger logger= LoggerFactory.getLogger(DateTimeUtil.class);
    private  static final String DEFAULT_DATE_PARTTERN="yyyy-MM-dd  HH:mm:ss";
    private  static  final  String DEFAULT_TIME_PARTTERN="HH:mm:ss";


    public static  String  getCurrentTime(){
        return  getCurrentTime(DEFAULT_TIME_PARTTERN);
    }

    public static String getCurrentTime(String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return sdf.format(timestamp);
    }



}
