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
public class SubscribeResp implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int subReqID;
    
    private int respCode;
    
    private String desc;

    public void setSubReqID(int subReqID) {
        this.subReqID = subReqID;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }   

    public int getSubReqID() {
        return subReqID;
    }

    public int getRespCode() {
        return respCode;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "SubscribeResp{" + "subReqID=" + subReqID + ", respCode=" + respCode + ", desc=" + desc + '}';
    }   //toString()        
    
}   //SubscribeResp
