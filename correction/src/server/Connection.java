/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author myriam.fort
 */
public class Connection implements Runnable{
private Server server;
private ServerSocket serverSocket;
public Connection(Server s) throws IOException
{
    server=s;
    serverSocket=new ServerSocket(server.getPort());
}
public void run()
{
 while(true)
 {
     Socket sockNewClient;
     try {
         sockNewClient = serverSocket.accept();
         ConnectedClient newClient = new ConnectedClient(server, sockNewClient);
        server.addClient(newClient);
        Thread threadNewClient = new Thread(newClient);
        threadNewClient.start();
     } catch (IOException ex) {
         System.out.println(ex.getMessage());
     }
     
 }
 }
}
