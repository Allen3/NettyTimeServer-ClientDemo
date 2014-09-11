/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nettyTimeServerDemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 *
 * @author allen
 */
public class TimeServer {

    public TimeServer() {
    }   //TimeServer()
    
    /**
     *
     * @param port
     * @throws Exception
     */
    public void bind(int port) throws Exception {
        
        EventLoopGroup bossGroup   = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        
        try {
            
            ServerBootstrap bootstrap = new ServerBootstrap();
            
            bootstrap.group(bossGroup, workerGroup).
                    channel(NioServerSocketChannel.class).
                    option(ChannelOption.SO_BACKLOG, 1024).
                    childHandler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel c) throws Exception {
                    c.pipeline().addLast(new LineBasedFrameDecoder(1024));
                    c.pipeline().addLast(new StringDecoder());
                    c.pipeline().addLast(new TimeServerHandler());                              
                }   //initChannel()
                        
                    });
            
            ChannelFuture future = bootstrap.bind(port).sync();
            
            future.channel().closeFuture().sync();                               
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }   //try-finally        
    }   //bind()            
    
}   //TimeServer
