/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nettytimeclientdemo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 *
 * @author allen
 */
public class TimeClient {

    public TimeClient() {
    }   //TimeClient()
    
    /**
     *
     * @param port
     * @param host
     * @throws Exception
     */
    public void connect(int port, String host) throws Exception {
        
        EventLoopGroup group = new NioEventLoopGroup();
        
        try {
            Bootstrap bootstrap = new Bootstrap();
            
            bootstrap.group(group).
                    channel(NioSocketChannel.class).
                    option(ChannelOption.TCP_NODELAY, true).
                    handler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel c) throws Exception {
                    c.pipeline().addLast(new LineBasedFrameDecoder(1024));
                    c.pipeline().addLast(new StringDecoder());
                    c.pipeline().addLast(new TimeClientHandler());                    
                }   //initChannel()
                        
                    });
            
            ChannelFuture future = bootstrap.connect(host, port).sync();
            
            future.channel().closeFuture().sync();                        
        } finally {
            group.shutdownGracefully();
        }   //try-finally
    }   //connect()
    
}   //TimeClient
