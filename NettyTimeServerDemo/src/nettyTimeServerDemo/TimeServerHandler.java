/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nettyTimeServerDemo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import static java.lang.System.currentTimeMillis;

/**
 *
 * @author allen
 */
public class TimeServerHandler extends ChannelHandlerAdapter {

    private int counter;
    
    public TimeServerHandler() {
        counter = 0;
    }   //TimeServerHandler()
        
    /**
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /*
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
                
        String body = new String(req, "UTF-8").substring(0, req.length - System.getProperty("line.separator").length());
        */
        
        String body = (String) msg;        
        
        System.out.println("The time server receive order : " + body + " ; the counter is : " + ++counter);
        
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                new java.util.Date(currentTimeMillis()).toString() : "BAD ORDER";        
        currentTime += System.getProperty("line.separator");
        
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.write(resp);
    }   //channelRead()
    
    /**
     *
     * @param ctx
     * @throws Exception
     */          
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }   //channelReadComplete()        
    
    /**
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }   //exceptionCaught
    
}   //TimeServerHandler
