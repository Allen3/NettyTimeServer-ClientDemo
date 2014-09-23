/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nettyjbossmarshallingclientdemo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import util.MarshallingCodeCFactory;

/**
 *
 * @author Allen Young
 */
public class SubReqClient {

    public SubReqClient() {
    }   //SubReqClient()
    
    public void connect(int port, String host) throws InterruptedException {
        
        EventLoopGroup group = new NioEventLoopGroup();
        
        try {
            
            Bootstrap bootstrap = new Bootstrap();
            
            bootstrap.group(group).
                    channel(NioSocketChannel.class).
                    option(ChannelOption.TCP_NODELAY, true).
                    handler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel c) throws Exception {
                    c.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                    c.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                    c.pipeline().addLast(new SubReqClientHandler());
                }   //initChannel()
                        
                    });
            
            ChannelFuture future = bootstrap.connect(host, port).sync();
            
            future.channel().closeFuture().sync();
            
        } finally {
            group.shutdownGracefully();
        }
        
    }   //connect()
    
}   //SubReqClient
