/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nettyjavaserializationserverdemo;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import util.SubscribeReq;
import util.SubscribeResp;

/**
 *
 * @author allen
 */
public class SubReqServerHandler extends ChannelHandlerAdapter{

    public SubReqServerHandler() {
    }   //SubReqServerHandler()

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReq req = (SubscribeReq) msg;
        
        if ("Allen Young".equalsIgnoreCase(req.getUserName())) {
            System.out.println("Service accept client subscribe req : {" +
                    req.toString() + "}");
            ctx.writeAndFlush(resp(req.getSubReqID()));
        }
        
    }   //channelRead()

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private SubscribeResp resp(int subReqID) {
        SubscribeResp resp = new SubscribeResp();
        resp.setSubReqID(subReqID);
        resp.setRespCode(0);
        resp.setDesc("Netty book order succeed!");
        return resp;        
    }   //resp()
           
}   //SubReqServerHandler
