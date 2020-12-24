package com.ckg.util;

import java.net.SocketAddress;
import io.netty.channel.Channel;

public class NettyUtil {

    public  static  String   praseChannelRemoteAddr(final Channel channel){
        if (null==channel){
            return "";
        }
        SocketAddress remote=channel.remoteAddress();
        final  String addr=remote!=null?remote.toString():"";
        if (addr.length()>0){
            int i = addr.lastIndexOf("/");
            if (i>0){
                return addr.substring(i+1);
            }
            return addr;
        }
        return "";
    }


}
