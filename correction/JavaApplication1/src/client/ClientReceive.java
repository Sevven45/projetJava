/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import Hibernate.Message;
import UI.UI;

import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author myriam.fort
 */
public class ClientReceive implements Runnable {

    private Client client;
    private Socket socket;
    private ObjectInputStream in;

    public ClientReceive(Client c, Socket soc) {
        client = c;
        socket = soc;
    }

    public void run() {
        try {
            in = new ObjectInputStream(socket.getInputStream());
            boolean isActive = true;
            
            while (isActive) {
                Message mess = (Message) in.readObject();
                
                 
            }
            client.disconnectedServer();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
