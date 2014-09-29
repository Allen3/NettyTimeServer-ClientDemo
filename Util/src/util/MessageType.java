/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

/**
 *
 * @author allen
 */
public enum MessageType {
    
    SERVICE_REQ((byte) 0), SERVICE_RESP((byte) 1), ONE_WAY((byte) 2), LOGIN_REQ((byte) 3), 
    LOGIN_RESP((byte) 4), HEARTBEAT_REQ((byte) 5), HEARTBEAT_RESP((byte) 6);
    
    private byte value;
         
    private MessageType(byte value) {
        this.value = value;
    }   //MessageType()    
    
    public byte value() {
        return this.value;
    }   //value()
    
}   //MessageType
