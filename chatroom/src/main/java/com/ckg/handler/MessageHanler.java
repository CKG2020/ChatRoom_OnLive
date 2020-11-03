package com.ckg.handler;

import com.alibaba.fastjson.JSONObject;
import com.ckg.entity.UserInfo;
import com.ckg.proto.ChatCode;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageHanler  extends SimpleChannelInboundHandler <TextWebSocketFrame> {
   private   static  final Logger  logger=LoggerFactory.getLogger(MessageHanler.class);
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame frame) throws Exception
    {
        UserInfo userInfo = UserInfoManager.getUserInfo(ctx.channel());
        if (userInfo!=null&&userInfo.isAuth()){
            JSONObject jsonObject = JSONObject.parseObject(frame.text());
            UserInfoManager.broadcastMess(userInfo.getUserId(), userInfo.getNick(),jsonObject.getString("mess"));
        }
    }


    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception{
        UserInfoManager.removeChannel(ctx.channel());
   UserInfoManager.broadCastInfo(ChatCode.SYS_USER_COUNT,UserInfoManager.getAuthUserCount());
super.channelUnregistered(ctx);
    }


    public void  exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        logger.error("connection error and close the channel", cause);
      UserInfoManager.removeChannel(ctx.channel());
      UserInfoManager.broadCastInfo(ChatCode.SYS_USER_COUNT,UserInfoManager.getAuthUserCount());


    }





}
