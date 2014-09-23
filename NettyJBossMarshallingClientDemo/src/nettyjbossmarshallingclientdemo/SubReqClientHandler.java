/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nettyjbossmarshallingclientdemo;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import util.SubscribeReq;

/**
 *
 * @author Allen Young
 */
public class SubReqClientHandler extends ChannelHandlerAdapter {

    public SubReqClientHandler() {
    }   //SubReqClientHandler()

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }   //channelReadComplete()

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Receive server response : {" + msg + "}");
    }   //channelRead()

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0;i < 10;i ++) {
            ctx.write(SubReq(i));
        }   //for
        ctx.flush();
    }   //channelActive()

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();        
    }

    private SubscribeReq SubReq(int i) {
        SubscribeReq req = new SubscribeReq();
        req.setSubReqID(i);
        req.setUserName("Allen Young");
        return req;        
    }   //SubReq()
    
    
    
}   //SubReqClientHandler
