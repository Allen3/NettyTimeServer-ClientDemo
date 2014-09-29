/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nettyprivateprotocolserverdemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import util.NettyConstant;
import util.NettyMessageDecoder;
import util.NettyMessageEncoder;

/**
 *
 * @author allen
 */
public class NettyServer {
    
    public void bind() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        
        
        ServerBootstrap serverBootstrap = new ServerBootstrap();
            
        serverBootstrap.group(bossGroup, workerGroup).
                channel(NioServerSocketChannel.class).
                option(ChannelOption.SO_BACKLOG, 100).
                handler(new LoggingHandler(LogLevel.INFO)).
                childHandler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
                ch.pipeline().addLast(new NettyMessageEncoder());
                ch.pipeline().addLast("readTimeoutHandler", new ReadTimeoutHandler(50));
                ch.pipeline().addLast(new LoginAuthRespHandler());
                ch.pipeline().addLast(new HeartBeatRespHandler());
                
            }   //initChannel()
                    
                });
                
            serverBootstrap.bind(NettyConstant.REMOTEIP, NettyConstant.PORT).sync();
            System.out.println("Netty server start OK : " + NettyConstant.REMOTEIP + " : " + NettyConstant.PORT);                
    }   //bind()
   
}   //NettyServer
