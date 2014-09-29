package util;


import java.io.IOException;
import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;
import org.jboss.marshalling.Unmarshaller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author allen
 */
public class MarshallingCodecFactory {
       
    protected static Marshaller buildMarshalling() throws IOException {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        
        configuration.setVersion(5);
        
        Marshaller marshaller = marshallerFactory.createMarshaller(configuration);
        
        return marshaller;        
    }   //buildMarshalling()
    
    protected static Unmarshaller buildUnmarshalling() throws IOException {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        
        configuration.setVersion(5);
        
        Unmarshaller unmarshaller = marshallerFactory.createUnmarshaller(configuration);
        
        return unmarshaller;
    }   //buildUnmarshallign()
    
}   //MarshallingCodecFactory
