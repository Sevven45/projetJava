/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import common.Message;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author myriam.fort
 */
public class ConnectedClient implements Runnable {

    private static Integer idCounter = 1;
    private Integer id;
    private Server server;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ConnectedClient(Server s, Socket soc) throws IOException {
        server = s;
        socket = soc;
        id = idCounter;
        idCounter++;
        out = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Nouvelle connexion, id = " + id);
    }

    public void run() {
        try {
            in = new ObjectInputStream(socket.getInputStream());
            boolean isActive = true;
            while (isActive) {
                Message mess = (Message) in.readObject();
                if (mess != null) {
                    mess.setId(String.valueOf(id));

                    server.broadcastMessage(mess, id);
                } else {
                    server.disconnectedClient(this);
                    isActive = false;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void sendMessage(Message mess) throws IOException {
        this.out.writeObject(mess);
        this.out.flush();
    }

    public void closeClient() throws IOException {
        this.in.close();
        this.out.close();
        this.socket.close();
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
}
