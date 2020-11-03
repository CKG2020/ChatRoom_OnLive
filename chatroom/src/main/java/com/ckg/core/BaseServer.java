package com.ckg.core;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.omg.SendingContext.RunTime;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public  abstract class BaseServer implements Server {
   protected  Logger logger= LoggerFactory.getLogger(getClass());
   protected  String host="localhost";
   protected   int port=8099;
   protected DefaultEventLoopGroup   defaultEventLoopGroup;
   protected NioEventLoopGroup  bossGroup;
   protected  NioEventLoopGroup workGroup;
   protected NioServerSocketChannel   serverSocketChannel;
   private ServerBootstrap serverBootstrap;

    public  void init(){
     defaultEventLoopGroup= new DefaultEventLoopGroup(8, new ThreadFactory() {
           private AtomicInteger index=new AtomicInteger(0);
           public Thread newThread(Runnable r) {
               return new Thread(r,"DEFAULTEVENTLOOPGROUP"+index.incrementAndGet());
           }
       });

     //为什么要这样子写　　这样子写的意义是啥呢　＊＊＊＊＊＊＊
        //  new ThreadFactory(------------------)
     bossGroup=new NioEventLoopGroup(Runtime.getRuntime().availableProcessors(), new ThreadFactory() {

         private  AtomicInteger   index=new AtomicInteger(0);
         public Thread newThread(Runnable r) {
             return new Thread(r,"BOSS_"+index.incrementAndGet());
         }
     });
     workGroup=new NioEventLoopGroup(Runtime.getRuntime().availableProcessors() * 10, new ThreadFactory() {

       private   AtomicInteger index=new AtomicInteger(0);
         public Thread newThread(Runnable r) {
             return new Thread(r,"WORK_"+index.incrementAndGet());
         }
     });
     serverBootstrap=new ServerBootstrap();
    }



    public void  shutdown(){
        if (defaultEventLoopGroup!=null){
            defaultEventLoopGroup.shutdownGracefully();
        }
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }


}
