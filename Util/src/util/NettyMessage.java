/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import util.Header;

/**
 *
 * @author allen
 */
public final class NettyMessage {
    private Header header;
    private Object body;

    public Header getHeader() {
        return header;
    }   //getHeader()

    public Object getBody() {
        return body;
    }   //getBody()

    public void setHeader(Header header) {
        this.header = header;
    }   //setHeader()

    public void setBody(Object body) {
        this.body = body;
    }   //setBody()

    @Override
    public String toString() {
        return "NettyMessage{" + "header=" + header + '}';
    }                                
}   //NettyMessage
