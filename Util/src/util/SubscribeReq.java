/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.io.Serializable;

/**
 *
 * @author allen
 */
public class SubscribeReq implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int subReqID;
    
    private String userName;

    public void setSubReqID(int subReqID) {
        this.subReqID = subReqID;
    }   //setSubReqID()

    public int getSubReqID() {
        return subReqID;
    }   //getSubReqID()
    
    public void setUserName(String userName) {
        this.userName = userName;
    }   //setUserName()
    
    public String getUserName() {
        return userName;
    }   //getUserName()

    @Override
    public String toString() {
        return "SubscribeReq{" + "subReqID=" + subReqID + ", userName=" + userName + '}';
    }   //toString()
    
}   //SubscribeReq
