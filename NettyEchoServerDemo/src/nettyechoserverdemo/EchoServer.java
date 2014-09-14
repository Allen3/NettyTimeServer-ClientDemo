/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nettyechoserverdemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 *
 * @author allen
 */
public class EchoServer {

    public EchoServer() {
    }   //EchoServer()
    
    public void bind(int port) throws InterruptedException  {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            
            bootstrap.group(bossGroup, workerGroup).
                    channel(NioServerSocketChannel.class).
                    option(ChannelOption.SO_BACKLOG, 100).
                    handler(new LoggingHandler(LogLevel.INFO)).
                    childHandler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel c) throws Exception {                    
                    ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                    
                    c.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
                    //c.pipeline().addLast(new FixedLengthFrameDecoder(10));
                    c.pipeline().addLast(new StringDecoder());
                    c.pipeline().addLast(new EchoServerHandler());
                }   //initChannel()
                        
                    });
            
            ChannelFuture future = bootstrap.bind(port).sync();
            
            future.channel().closeFuture().sync();                        
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }   //try-finally
        
        
    }   //bind()
    
}   //EchoServer
