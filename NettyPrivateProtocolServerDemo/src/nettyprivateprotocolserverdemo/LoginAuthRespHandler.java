/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nettyprivateprotocolserverdemo;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import util.Header;
import util.MessageType;
import util.NettyMessage;

/**
 *
 * @author allen
 */
public class LoginAuthRespHandler extends ChannelHandlerAdapter {
    
    private Map<String, Boolean> nodeCheck = new ConcurrentHashMap<String, Boolean>();
    private String[] whiteList = {"127.0.0.1", "192.168.19.22"};

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        
        if (message.getHeader() != null &&
                message.getHeader().getType() == MessageType.LOGIN_REQ.value()) {
            String nodeIndex = ctx.channel().remoteAddress().toString();
            
            NettyMessage loginResp = null;
            if (nodeCheck.containsKey(nodeIndex)) {
                loginResp = buildResponse((byte) -1);
            } else {
                InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
                String ip = address.getAddress().getHostAddress();                
                boolean isOK = false;
                
                for (String whiteIP : whiteList) {
                    if (whiteIP.equals(ip)) {
                        isOK = true;
                        break;
                    }
                }                                
                
                if (isOK) {
                    loginResp = buildResponse((byte) 0);
                    nodeCheck.put(nodeIndex, true);
                } else {
                    loginResp = buildResponse((byte) -1);
                }                                
            }    
            
            System.out.println("The login response is : " + loginResp + " body {" + loginResp.getBody() + "}");
            ctx.writeAndFlush(loginResp);                                
        } else {
            ctx.fireChannelRead(msg);
        }        
        
    }   //channelRead()

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        nodeCheck.remove(ctx.channel().remoteAddress().toString());
        ctx.close();
        ctx.fireExceptionCaught(cause);
    }   //exceptionCaught()

    private NettyMessage buildResponse(byte result) {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        
        header.setType(MessageType.LOGIN_RESP.value());
        message.setHeader(header);
        message.setBody(result);
        
        return message;
    }   //buildResponse()
    
    
    
    
}   //LoginAuthRespHandler
