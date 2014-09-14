/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nettyechoserverdemo;

/**
 *
 * @author allen
 */
public class NettyEchoServerDemo {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);                
            } catch (NumberFormatException e) {                
            }
        }
        
        new EchoServer().bind(port);
        
    }   //main()
    
}   //NettyEchoServerDemo
