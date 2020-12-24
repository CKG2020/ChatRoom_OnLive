//package com.service.impl;
//
//import com.constant.CommonConstant;
//import com.entity.Message;
//import com.entity.User;
//import com.service.ChatSessionService;
//import com.utils.CoreUtil;
//import lombok.extern.slf4j.Slf4j;
//import netscape.javascript.JSObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import com.alibaba.fastjson.JSONObject;
//
//import java.util.Date;
//import java.util.List;
//@Slf4j
//@Service
//public class ChatSessionServiceImpl implements ChatSessionService {
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//
//    public User findById(String id) {
//        if (id!=null){
//            String  value=null;
//            if (id.startsWith(CommonConstant.USER_PREFIX)){
//                 value = redisTemplate.boundValueOps(id).get();
//            }
//            else{
//                value=redisTemplate.boundValueOps(CommonConstant.USER_PREFIX+id).get();
//            }
////            转换为ｊｓｏｎ  ge shi
//            JSONObject object = JSONObject.parseObject(value);
//            if (object!=null){
//                //zhuan huan wei java  dui xiang
//                return  object.toJavaObject(User.class);
//            }
//
//        }
//        return null;
//    }
//
//    public void pushMessage(String formId, String toId, String message) {
//        Message entity = new Message();
//        entity.setMessage(message);
//        entity.setFrom(this.findById(fromId));
//        entity.setTime(CoreUtil.format(new Date()));
//    }
//
//    public List<User> onlineList() {
//        return null;
//    }
//
//    public List<Message> commonList() {
//        return null;
//    }
//
//    public List<Message> selfList(String formId, String toId) {
//        return null;
//    }
//
//    public void delete(String id) {
//
//    }
//}
