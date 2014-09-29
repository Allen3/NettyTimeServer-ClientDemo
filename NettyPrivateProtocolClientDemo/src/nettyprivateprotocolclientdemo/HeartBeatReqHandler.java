/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nettyprivateprotocolclientdemo;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import util.Header;
import util.MessageType;
import util.NettyMessage;

/**
 *
 * @author allen
 */
public class HeartBeatReqHandler extends ChannelHandlerAdapter {
    private volatile ScheduledFuture<?> heartBeat;    
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        
        if (message.getHeader() != null && 
                message.getHeader().getType() == MessageType.LOGIN_RESP.value()) {
            heartBeat = ctx.executor().scheduleAtFixedRate(
                    new HeartBeatReqHandler.HeartBeatTask(ctx), 0, 5000, TimeUnit.MILLISECONDS);                                    
        } else if (message.getHeader() != null &&
                message.getHeader().getType() == MessageType.HEARTBEAT_RESP.value()) {
            System.out.println("Client receive server heart beat message : ---> " + message);
        } else {
            ctx.fireChannelRead(msg);
        }                
    }   //channelRead()

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (heartBeat != null) {
            heartBeat.cancel(true);
            heartBeat = null;
        }
        ctx.fireExceptionCaught(cause);
    }   //exceptionCaught()

    private class HeartBeatTask implements Runnable {
        private final ChannelHandlerContext ctx;
        
        public HeartBeatTask(ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }   //HeartBeatTask()

        @Override
        public void run() {
            NettyMessage heartBeat = buildHeartBeat();
            System.out.println("Client send heart beat message to server : ---> " + heartBeat);
            ctx.writeAndFlush(heartBeat);            
        }   //run()

        private NettyMessage buildHeartBeat() {
            NettyMessage message = new NettyMessage();
            Header header = new Header();
            
            header.setType(MessageType.HEARTBEAT_REQ.value());
            message.setHeader(header);
            
            return message;            
        }   //buildHeartBeat()
        
    }   //HeartBeatTask
    
}   //HeartBeatReqHandler
