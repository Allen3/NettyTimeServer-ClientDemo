/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import org.jboss.marshalling.ByteInput;
import org.jboss.marshalling.Unmarshaller;

/**
 *
 * @author allen
 */
class MarshallingDecoder {

    private final Unmarshaller unmarshaller;

    public MarshallingDecoder() throws IOException {
        this.unmarshaller = MarshallingCodecFactory.buildUnmarshalling();
    }   //MarshallingDecoder()
       
    protected Object decode(ByteBuf frame) throws IOException, ClassNotFoundException {
        int objectSize = frame.readInt();        
        ByteBuf buf = frame.slice(frame.readerIndex(), objectSize);
        ByteInput input = new ChannelBufferByteInput(buf);
        
        try {
            unmarshaller.start(input);
            Object obj = unmarshaller.readObject();
            unmarshaller.finish();
            frame.readerIndex(frame.readerIndex() + objectSize);
            
            return obj;                        
        } finally {
            unmarshaller.close();
        }                                
    }   //decoder()
    
}   //MarshallingDecoder
