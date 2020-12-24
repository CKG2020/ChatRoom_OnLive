package com.service;

import com.entity.Message;
import com.entity.User;

import java.util.List;

public interface ChatSessionService {

    User findById(String id);

    void pushMessage(String formId,String toId,String message);

    List<User> onlineList();

    List<Message> commonList();
    List<Message> selfList(String formId,String toId);
    void delete(String id);


}
