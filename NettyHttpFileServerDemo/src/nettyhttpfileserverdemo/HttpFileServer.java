/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nettyhttpfileserverdemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 *
 * @author allen
 */
public class HttpFileServer {

    public HttpFileServer() {
    }   //HttpFileServer()
    
    public void run(final int port, final String url) throws InterruptedException {
        
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        
        try {
            
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            
            serverBootstrap.group(bossGroup, workerGroup).
                    channel(NioServerSocketChannel.class).
                    childHandler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel c) throws Exception {
                    c.pipeline().addLast("http-decoder", new HttpRequestDecoder());
                    c.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
                    c.pipeline().addLast("http-encoder", new HttpResponseEncoder());
                    c.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                    c.pipeline().addLast("fileServerHandler", new HttpFileServerHandler(url));                    
                }   //initChannel()
                        
                    });
            
            ChannelFuture future = serverBootstrap.bind("192.168.1.108", port).sync();
            System.out.println("HTTP file directory server lauch, address is : " + "http://192.168.1.108:" + port + url);
            
            future.channel().closeFuture().sync();
            
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }   //try-finally
        
    }   //run()
    
}   //HttpFileServer
