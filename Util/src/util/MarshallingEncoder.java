/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.Marshaller;

/**
 *
 * @author allen
 */
public class MarshallingEncoder {
    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];
    Marshaller marshaller;

    //??????
    public MarshallingEncoder() {
        
    }   //MarshallingEncoder()
    
    protected void encode(Object msg, ByteBuf out) throws Exception {
        try {
            int lengthPos = out.writerIndex();
            out.writeBytes(LENGTH_PLACEHOLDER);
            
            ChannelBufferByteOutput
            
            
        } finally {
            marshaller.close();
        }   //try-finally
    }   //encode()
    
    
    
    
}   //MarshallingEncoder
