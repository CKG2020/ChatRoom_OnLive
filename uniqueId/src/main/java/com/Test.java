package com;
//import org.apache.commons.lang3.StringUtils;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//"yyMMddHHmmssSS"
public class Test {
    public static String getYYMMDDHHNumber(String merchId) {
//        SimpleDateFormat构造函数的参数：
//
//        G 年代标志符
//        y 年
//        M 月
//        d 日
//        h 时 在上午或下午 (1~12)
//        H 时 在一天中 (0~23)
//        m 分
//        s 秒
//        S 毫秒
//        E 星期
//        D 一年中的第几天
//        F 一月中第几个星期几
//        w 一年中第几个星期
//        W 一月中第几个星期
//        a 上午 / 下午 标记符
//        k 时 在一天中 (1~24)
//        K 时 在上午或下午 (0~11)
//        z 时区
        StringBuffer orderNo = new StringBuffer(new SimpleDateFormat("yyMMddHHmmssSS").format(new Date()));
        if (StringUtils.isNotBlank(merchId)) {
            if (merchId.length() > 3) {
                orderNo.append(merchId.substring(0, 3));
            } else {
                orderNo.append(merchId);
            }
        }
            int orderLength = orderNo.toString().length();
            String randomNum = getRandomLength(20 - orderLength);
            orderNo.append(randomNum);
            return orderNo.toString();


    }


    private static String getRandomLength(int size) {
        if (size>8||size<1){
            return "";
        }
        Random random = new Random();
        StringBuffer end= new StringBuffer("1");
        StringBuffer sta = new StringBuffer("9");
        for (int i = 0; i <size ; i++) {
            end.append("0");
            sta.append("0");
        }
    int randomId=random.nextInt(Integer.valueOf(sta.toString()))+Integer.valueOf(end.toString());
        return String.valueOf(randomId);





    }


    public static void main(String[] args) {
        // isNotEmpty==判断某字符串是否非空
//        System.out.println(StringUtils.isNotEmpty(null)); // = false;
//        System.out.println(StringUtils.isNotEmpty("")); // false;
//        System.out.println(StringUtils.isNotEmpty(" "));// true;
//        System.out.println(StringUtils.isNotEmpty("bob")); // true;
//
//        // isNotBlank：判断某字符串是否不为空且长度不为0且不由空白符(whitespace)构成，
//        System.err.println(StringUtils.isNotBlank(null)); // false
//        System.err.println(StringUtils.isNotBlank("")); // false
//        System.err.println(StringUtils.isNotBlank(" ")); // false
//        System.err.println(StringUtils.isNotBlank("\t \n \f \r")); // false
//        System.out.println(StringUtils.isNotBlank("xxxx"));
      final String  merchId="12345";
        final List<String> orderNos = Collections.synchronizedList(new ArrayList<String>());
        IntStream.range(0,1000).parallel().forEach(i->{
            orderNos.add(getYYMMDDHHNumber(merchId));
        });
        List<String> filterOrderNos = orderNos.stream().distinct().collect(Collectors.toList());
        System.out.println("订单数"+orderNos.size());
        System.out.println("过滤后重复订单数"+filterOrderNos.size());
        System.out.println("重复订单数"+(orderNos.size()-filterOrderNos.size()));

    }

}