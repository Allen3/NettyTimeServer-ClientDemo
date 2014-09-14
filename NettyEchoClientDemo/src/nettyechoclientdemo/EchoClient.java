/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nettyechoclientdemo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 *
 * @author allen
 */
public class EchoClient {

    public EchoClient() {
    }   //EchoClient()
    
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
                    ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                    c.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
                    c.pipeline().addLast(new StringDecoder());
                    c.pipeline().addLast(new EchoClientHandler());                   
                }   //initChannel()                
            });
            
            ChannelFuture future = bootstrap.connect(host, port).sync();
            
            future.channel().closeFuture().sync();            
        } finally {
            group.shutdownGracefully();
        }   //try-finally
        
    }   //connect()
    
}   //EchoClient
