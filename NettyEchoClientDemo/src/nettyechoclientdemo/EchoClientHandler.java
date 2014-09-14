/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nettyechoclientdemo;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 *
 * @author allen
 */
public class EchoClientHandler extends ChannelHandlerAdapter {

    private static final String ECHO_REQ = "Hi, Allen. Welcome to Netty.";
    
    private int counter;        
    
    public EchoClientHandler() {
        counter = 0;        
    }   //EchoClientHandler()
    
    @Override
    public void channelActive (ChannelHandlerContext ctx) throws Exception {
        for (int i = 0;i < 100;i ++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer((ECHO_REQ + "$_").getBytes()));
        }
    }   //channelRead()
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("This is " + ++ counter + " times receive server : [" + (String) msg + "]");
    }   //channelRead()
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }   //channelReaComplete()
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }   //exceptionCaught
    
}   //EchoClientHandler