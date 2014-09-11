/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nettytimeclientdemo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import java.util.logging.Logger;

/**
 *
 * @author allen
 */
public class TimeClientHandler extends ChannelHandlerAdapter {

    private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());
    //private final ByteBuf firstMessage;
    
    private int counter;
    
    private byte[] req;
    
    /**
     *  Creates a client-side handler.
     */
    public TimeClientHandler() {
        counter = 0;
        
        req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
        //firstMessage = Unpooled.buffer(req.length);
        //firstMessage.writeBytes(req);
    }   //TimeClientHandler()
    
    /**
     *
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ByteBuf message = null;
        
        for (int i = 0;i < 100;i ++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }   //for
                
    }   //channelActive()
    
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
        
        //String body = new String(req, "UTF-8").substring(0, req.length - System.getProperty("line.separator").length());
        String body = new String(req, "UTF-8");
        */
        
        String body = (String) msg;       
        
        System.out.println("Now is : " + body + " ; the counter is : " + ++counter);
    }   //channelRead()
    
    /**
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.warning("Unexpected exception from downstream : " + cause.getMessage());
        ctx.close();
    }   //exceptionCaught()
    
}   //TimeClientHandler
