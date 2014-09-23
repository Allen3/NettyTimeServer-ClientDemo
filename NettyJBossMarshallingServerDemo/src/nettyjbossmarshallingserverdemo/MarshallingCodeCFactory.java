/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nettyjbossmarshallingserverdemo;

import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

/**
 *
 * @author allen
 */
public final class MarshallingCodeCFactory {
    
    public static MarshallingDecoder buildMarshallingDecoder() {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");        
        final MarshallingConfiguration marshallingConfiguration = new MarshallingConfiguration();
        
        marshallingConfiguration.setVersion(5);
        
        UnmarshallerProvider unmarshallerProvider = new DefaultUnmarshallerProvider(marshallerFactory, marshallingConfiguration);
        
        MarshallingDecoder decoder = new MarshallingDecoder(unmarshallerProvider, 1024);
        
        return decoder;        
    }   //buildMarshallingDecoder()
    
    public static MarshallingEncoder buildMarshallingEncoder() {
        //...
    }   //buildMarshallingEncoder()
    
    
}   //MarshallingCodeCFactory
