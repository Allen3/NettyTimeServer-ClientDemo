/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author allen
 */
public final class Header {
    private int crcCode;
    private int length;
    private long sessionID;
    private byte type;
    private byte priority;
    private Map<String, Object> attachment = new HashMap<String, Object>();

    public Header() {
        this.crcCode = 0xabef0101;
    }   //crcCode();

    public int getCrcCode() {
        return crcCode;
    }   //getCrcCode()

    public int getLength() {
        return length;
    }   //getLength()

    public long getSessionID() {
        return sessionID;
    }   //getSessionID()

    public byte getType() {
        return type;
    }   //getType()

    public byte getPriority() {
        return priority;
    }   //getPriority()

    public Map<String, Object> getAttachment() {
        return attachment;
    }   //getAttachment()

    public void setCrcCode(int crcCode) {
        this.crcCode = crcCode;
    }   //setCrcCode()
       
    public void setLength(int length) {
        this.length = length;
    }   //setLength()

    public void setSessionID(long sessionID) {
        this.sessionID = sessionID;
    }   //setSessionID()

    public void setType(byte type) {
        this.type = type;
    }   //setType()

    public void setPriority(byte priority) {
        this.priority = priority;
    }   //setPriority()

    public void setAttachment(Map<String, Object> attachment) {
        this.attachment = attachment;
    }   //setAttachment()

    @Override
    public String toString() {
        return "Header{" + "crcCode=" + crcCode + ", length=" + length + ", sessionID=" + sessionID + ", type=" + type + ", priority=" + priority + ", attachment=" + attachment + '}';
    }                
}   //Header
