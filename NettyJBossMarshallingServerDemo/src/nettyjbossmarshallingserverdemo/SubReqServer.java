/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nettyjbossmarshallingserverdemo;

import util.MarshallingCodeCFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 *
 * @author allen
 */
public class SubReqServer {

    public SubReqServer() {
    }   //SubReqServer()
    
    public void bind(int port) throws InterruptedException {
        
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            
            serverBootstrap.group(bossGroup, workerGroup).
                    channel(NioServerSocketChannel.class).
                    option(ChannelOption.SO_BACKLOG, 100).
                    handler(new LoggingHandler(LogLevel.INFO)).
                    childHandler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel c) throws Exception {
                    c.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                    c.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                    c.pipeline().addLast(new SubReqServerHandler());
                }   //initChannel()
                        
                    });
            
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            
            channelFuture.channel().closeFuture().sync();                                    
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }   //try-finally
        
    }   //bind()
    
}   //SubReqServer
