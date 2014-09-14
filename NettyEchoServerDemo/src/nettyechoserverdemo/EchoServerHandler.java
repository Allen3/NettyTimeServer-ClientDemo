/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nettyechoserverdemo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 *
 * @author allen
 */
public class EchoServerHandler extends ChannelHandlerAdapter {

    private int counter;
    
    public EchoServerHandler() {
        counter = 0;                
    }   //EchoServerHandler()
        
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        
        System.out.println("This is " + ++ counter + " times receive client : [" + body + "]");
        
        body += "$_";
        
        ByteBuf echo = Unpooled.copiedBuffer(body.getBytes());
        
        ctx.writeAndFlush(echo);        
    }   //channelRead()
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }   //exceptionCaught()
    
}   //EchoServerHandler
