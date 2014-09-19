/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nettyhttpfileserverdemo;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelProgressiveFuture;
import io.netty.channel.ChannelProgressiveFutureListener;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaders.isKeepAlive;
import static io.netty.handler.codec.http.HttpHeaders.setContentLength;
import static io.netty.handler.codec.http.HttpMethod.GET;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpResponseStatus.FORBIDDEN;
import static io.netty.handler.codec.http.HttpResponseStatus.METHOD_NOT_ALLOWED;
import static io.netty.handler.codec.http.HttpResponseStatus.NOT_FOUND;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.stream.ChunkedFile;
import io.netty.util.CharsetUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/**
 *
 * @author allen
 */
public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    
    private final String url;

    public HttpFileServerHandler(String url) {
        this.url = url;
    }   //HttpFileServerHandler()

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        if (!request.getDecoderResult().isSuccess()) {
            sendError(ctx, BAD_REQUEST);
            return;
        }
        
        if (request.getMethod() != GET) {
            sendError(ctx, METHOD_NOT_ALLOWED);
            return;
        }   
        
        final String uri = request.getUri();
        final String path = sanitizeUti(uri);
        
        if (path == null) {
            sendError(ctx, FORBIDDEN);
            return;
        }
        
        File file = new File(path);
        if (file.isHidden() || !file.exists()) {
            sendError(ctx, NOT_FOUND);
            return;
        }
        
        if (file.isDirectory()) {
            if (uri.endsWith("/")) {
                sendListing(ctx, file);
            } else {
                sendRedirect(ctx, uri + '/');
            }
            return;
        }
        
        if (!file.isFile()) {
            sendError(ctx, FORBIDDEN);
            return;        
        }
        
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
        } catch (FileNotFoundException fnfe) {
            sendError(ctx, NOT_FOUND);
        }   //try-catch
        
        long fileLength = randomAccessFile.length();
        
        
        HttpResponse response = new DefaultHttpResponse(HTTP_1_1, OK);
        setContentLength(response, fileLength);
        setContentTypeHeader(response, file);
        
        if (isKeepAlive(request)) {
            response.headers().set(CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
        }           
        ctx.write(response);
        
        ChannelFuture sendFileFuture = ctx.write(new ChunkedFile(randomAccessFile, 0, fileLength, 8192), 
                ctx.newProgressivePromise());
        
        sendFileFuture.addListener(new ChannelProgressiveFutureListener() {

            @Override
            public void operationProgressed(ChannelProgressiveFuture future, long progress, long total) throws Exception {
                if (total < 0) {
                    System.err.println("Transfer progress: " + progress);
                } else {
                    System.err.println("Transfer progress: " + progress + "/" + total);
                }
            }   //operationProgressed()

            @Override
            public void operationComplete(ChannelProgressiveFuture future) throws Exception {
                System.out.println("Transfer Complete");
            }   //operationComplete()
            
        });
        
        ChannelFuture lastContentFuture = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
        
        if (!isKeepAlive(request)) {
            lastContentFuture.addListener(ChannelFutureListener.CLOSE);
        }
        
        //???
        //@Override
        //ExceptionCaught()
        
    }   //messageReceived()

    
    
    private void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
        FullHttpResponse reponse = new DefaultFullHttpResponse(HTTP_1_1, status, 
                Unpooled.copiedBuffer("Failure: " + status.toString() + System.getProperty("line.separator"), CharsetUtil.UTF_8));
    }   //sendError()

    private String sanitizeUti(String uri) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void sendListing(ChannelHandlerContext ctx, File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void sendRedirect(ChannelHandlerContext ctx, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setContentTypeHeader(HttpResponse response, File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}   //HttpFileServerHandler
