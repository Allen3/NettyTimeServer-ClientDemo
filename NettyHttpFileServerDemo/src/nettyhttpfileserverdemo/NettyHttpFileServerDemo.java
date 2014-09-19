/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nettyhttpfileserverdemo;

/**
 *
 * @author allen
 */
public class NettyHttpFileServerDemo {

    private static final String DEFAULT_URL = "/src/com/phei/netty";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException nfe) {                
            }   //try-catch
        }
        
        String url = DEFAULT_URL;        
                
        if (args.length > 1) {
            url = args[1];
        }
        
        new HttpFileServer().run(port, url);                        
    }   //main()
    
}   //nettyHttpFileServerDemo
