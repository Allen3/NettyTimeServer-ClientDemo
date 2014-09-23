/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nettyjbossmarshallingclientdemo;

/**
 *
 * @author Allen Young
 */
public class NettyJBossMarshallingClientDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {                
            }   //try-catch
        }
        
        new SubReqClient().connect(port, "127.0.0.1");
        
    }   //main()
    
}   //NettyJBossMarshallingClientDemo
