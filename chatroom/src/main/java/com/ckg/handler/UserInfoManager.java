package com.ckg.handler;

import com.ckg.entity.UserInfo;
import com.ckg.proto.ChatProto;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ckg.util.BlankUtil;
import com.ckg.util.NettyUtil;

import io.netty.channel.Channel;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class UserInfoManager {
    private static final Logger logger = LoggerFactory.getLogger(UserInfoManager.class);
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
    private static ConcurrentHashMap<Channel, UserInfo> userInfos = new ConcurrentHashMap<Channel, UserInfo>();
    private static AtomicInteger userCount = new AtomicInteger(0);


    public static void addchannel(Channel channel) {
        String s = NettyUtil.praseChannelRemoteAddr(channel);

        if (!channel.isActive()) {
            logger.error("channel is not active ,address:{}", s);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setAddr(s);
        userInfo.setChannel(channel);
        userInfo.setTime(System.currentTimeMillis());
        userInfos.put(channel, userInfo);

    }

    public static boolean saveUser(Channel channel, String nick) {
        UserInfo userInfo = userInfos.get(channel);
        if (userInfo != null) {
            return false;
        }
        if (!channel.isActive()) {
            logger.error("channel is  not Active:{},", userInfo.getAddr(), nick);
            return false;
        }
        userCount.incrementAndGet();
        //增加一个认证用户
        userInfo.setNick(nick);
        userInfo.setAuth(true);
        userInfo.setUserId();
        userInfo.setTime(System.currentTimeMillis());
        return true;
    }

    //从缓存中移除channel,并且或关闭channel
    public static void removeChannel(Channel channel) {
        try {
            logger.warn("channel will be  removed,address  is :{}", NettyUtil.praseChannelRemoteAddr(channel));
            readWriteLock.writeLock().lock();
            channel.close();
            UserInfo userInfo = userInfos.get(channel);
            if (userInfo != null) {
                UserInfo remove = userInfos.remove(channel);
                if (remove != null & remove.isAuth()) {
                    userCount.decrementAndGet();
                }


            }


        } finally {
            readWriteLock.writeLock().unlock();
        }


    }

    //广播普通消息
    public static void broadcastMess(int uid, String nick, String message) {

        if (!BlankUtil.isBlank(message)) {
            try {
                readWriteLock.readLock().lock();
                Set<Channel> keySet = userInfos.keySet();
                for (Channel ch : keySet) {
                    UserInfo userInfo = userInfos.get(ch);
                    if (userInfo == null || !userInfo.isAuth())
                        continue;
                    ch.writeAndFlush(new TextWebSocketFrame(ChatProto.buildMessProto(uid, nick, message)));

                }
            } finally {
                readWriteLock.readLock().lock();
            }
        }
    }

    public static void broadCastInfo(int code, Object mess) {
        readWriteLock.readLock().lock();
        Set<Channel> keySet = userInfos.keySet();
        for (Channel ch : keySet) {
            UserInfo userInfo = userInfos.get(ch);
            if (userInfo == null || !userInfo.isAuth())
                continue;
            ch.writeAndFlush(new TextWebSocketFrame(ChatProto.buildSysProto(code, mess)));
        }

        readWriteLock.readLock().unlock();

    }

    public static void broadCastPing() {
        readWriteLock.readLock().lock();
        logger.info("broadCastPing  usercount:{}", userCount.intValue());

        Set<Channel> keySet = userInfos.keySet();
        for (Channel ch : keySet) {
            UserInfo userInfo = userInfos.get(ch);
            if (userInfo == null || userInfo.isAuth())
                continue;
            ch.writeAndFlush(new TextWebSocketFrame(ChatProto.buildPingProto()));
        }
        readWriteLock.readLock().unlock();


    }

    public static void sendInfo(Channel channel, int code, Object mess) {
        channel.writeAndFlush(new TextWebSocketFrame(ChatProto.buildSysProto(code, mess)));

    }


    public static void sendPong() {

    }

    //扫描并关闭失效的ｃｈａｎｎｅｌ
    public static void scanNotActiveChannel() {
        Set<Channel> KeySet = userInfos.keySet();
        for (Channel ch : KeySet) {
            UserInfo userInfo = userInfos.get(ch);
            if (userInfo == null) continue;
            if (!ch.isOpen() || !ch.isActive() || !userInfo.isAuth() &&
                    (System.currentTimeMillis() - userInfo.getTime() > 100)
            ) {
                removeChannel(ch);
            }
        }
    }


    public static UserInfo getUserInfo(Channel channel) {
        return userInfos.get(channel);
    }
    public static int getAuthUserCount() {
        return userCount.get();
    }
    public static void updateUserTime(Channel channel) {
        UserInfo userInfo = getUserInfo(channel);
        if (userInfo != null) {
            userInfo.setTime(System.currentTimeMillis());
        }
    }
}
