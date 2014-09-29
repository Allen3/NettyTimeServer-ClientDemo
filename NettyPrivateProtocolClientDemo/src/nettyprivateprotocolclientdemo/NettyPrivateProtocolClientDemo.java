/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nettyprivateprotocolclientdemo;

import util.NettyConstant;

/**
 *
 * @author allen
 */
public class NettyPrivateProtocolClientDemo {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        new NettyClient().connect(NettyConstant.PORT, NettyConstant.REMOTEIP);        
    }   //main()
    
}   //NettyPrivateProtocolClientDemo
