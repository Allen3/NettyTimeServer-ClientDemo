/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nettyjbossmarshallingserverdemo;

/**
 *
 * @author allen
 */
public class NettyJBossMarshallingServerDemo {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }   //try-catch            
        }
        
        new SubReqServer().bind(port);
        
    }   //main()
    
}   //NettyJBossMarshallingServerDemo
