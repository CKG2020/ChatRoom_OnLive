package com.ckg.handler;
import com.alibaba.fastjson.JSONObject;
import com.ckg.proto.ChatCode;
import com.ckg.util.Contants1;
import com.ckg.entity.UserInfo;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ckg.util.NettyUtil;

public class UserAuthHandler  extends SimpleChannelInboundHandler<Object> {
    private static final Logger logger = LoggerFactory.getLogger(UserAuthHandler.class);

    private WebSocketServerHandshaker handshake;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {

        }

    }


    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
        if (!request.decoderResult().isSuccess() || "websocket".equals(request.headers().get("Upgrade"))) {
            logger.warn("protobuf  don't  support websocket");
            ctx.channel().close();
            return;
        }
        WebSocketServerHandshakerFactory handshakerFactory = new WebSocketServerHandshakerFactory(
                Contants1.WEBSOCKET_URL, null, true);
        handshake = handshakerFactory.newHandshaker(request);
        if (handshake == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse((Channel) ctx.handler());
        } else {
            //动态加入websocket 的编解码处理
            handshake.handshake(ctx.channel(), request);
            UserInfo userInfo = new UserInfo();
            userInfo.setAddr(NettyUtil.praseChannelRemoteAddr(ctx.channel()));
            //存储已经连接的ｃｈａｎｎｅｌ
            UserInfoManager.addchannel(ctx.channel());


        }

    }

    private void handleWebSocket(ChannelHandlerContext ctx, WebSocketFrame frame) {
        //判断是否关闭链路命令
        if (frame instanceof CloseWebSocketFrame) {
            handshake.close(ctx.channel(), ((CloseWebSocketFrame) frame).retain());
            UserInfoManager.removeChannel(ctx.channel());
            return;
        }
        //判断是否Ping消息
        if (frame instanceof PingWebSocketFrame) {
            logger.info("ping message:{}", frame.content().retain());
            ctx.writeAndFlush(new PongWebSocketFrame(frame.content().retain()));
            return;
        }

        if (frame instanceof PongWebSocketFrame) {
            logger.info("pong message:{}", frame.content().retain());
            ctx.writeAndFlush(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
  if (!(frame instanceof TextWebSocketFrame)){
      throw new UnsupportedOperationException(frame.getClass().getName()+"frame type not supported")
  }
        String message = ((TextWebSocketFrame) frame).text();
        JSONObject jsonObject = JSONObject.parseObject(message);
        Integer code = jsonObject.getInteger("code");
        Channel channel = ctx.channel();
        switch (code){
            case ChatCode.PING_PONG:
            case  ChatCode.PONG_CODE:
                UserInfoManager.updateUserTime(channel);
                logger.info("receive pong message,address :{}",NettyUtil. praseChannelRemoteAddr(channel));
                return;
            case ChatCode.AUTH_CODE:
                UserInfoManager.saveUser(channel,jsonObject.getString("nick"))


        }




    }
}




