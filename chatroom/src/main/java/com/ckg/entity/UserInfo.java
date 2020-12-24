package com.ckg.entity;

import  io.netty.channel.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class UserInfo {

 private  static  AtomicInteger  uidGener =  new   AtomicInteger(1000);
    private  boolean isAuth=false;
    private  long   time=0;
    private  int userId;
    private  String nick;
    private  String addr;
    private Channel  channel;


    public static AtomicInteger getUidGener() {
        return uidGener;
    }

    public static void setUidGener(AtomicInteger uidGener) {
        UserInfo.uidGener = uidGener;
    }

    public boolean isAuth() {
        return isAuth;
    }

    public void setAuth(boolean auth) {
        isAuth = auth;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId() {
        this.userId=uidGener.incrementAndGet();

    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
