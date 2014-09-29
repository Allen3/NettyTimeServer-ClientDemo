/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nettyprivateprotocolserverdemo;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import util.Header;
import util.MessageType;
import util.NettyMessage;

/**
 *
 * @author allen
 */
public class HeartBeatRespHandler extends ChannelHandlerAdapter {

    
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        
        if (message.getHeader() != null &&
                message.getHeader().getType() == MessageType.HEARTBEAT_REQ.value()) {
            System.out.println("Receive client heart beat message : ---> " + message);
            
            NettyMessage heartBeat = buildHeartBeat();
            
            System.out.println("Send heart beat response message to client : ---> " + heartBeat);
            ctx.writeAndFlush(heartBeat);
        }
        
    }   //channelRead()

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.fireExceptionCaught(cause);
    }   //exceptionCaught()

    private NettyMessage buildHeartBeat() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        
        header.setType(MessageType.HEARTBEAT_RESP.value());
        message.setHeader(header);
        
        return message;        
    }   //buildHeartBeat()
    
    
    
}   //HeartBeatRespHandler
