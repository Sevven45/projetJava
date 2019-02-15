/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import common.Message;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author myriam.fort
 */
public class Server {

    private int port;
    private List<ConnectedClient> clients;

    public Server(int p) {
        port = p;
        clients = new ArrayList<ConnectedClient>();
        Thread threadConnection;
        try {
            threadConnection = new Thread(new Connection(this));
            threadConnection.start();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }

    }

    public void addClient(ConnectedClient newClient) throws IOException {
        for (ConnectedClient client : clients) {
            client.sendMessage(new Message("server", "Le client " + newClient.getId() + " vient de se connecter"));
        }
        this.clients.add(newClient);

    }

    public void broadcastMessage(Message mess, int id) throws IOException {
        for (ConnectedClient client : clients) {
            if (client.getId() != id) {
                client.sendMessage(mess);
            }
        }
    }

    public void disconnectedClient(ConnectedClient discClient) throws IOException {
        //déconnecte le client
        discClient.closeClient();
        //supprime le client de la liste des clients connectés
        clients.remove(discClient);
        //envoi d'un message aux autres clients
        for (ConnectedClient client : clients) {
            client.sendMessage(new Message("server", "Le client "
                    + discClient.getId() + " nous a quitté"));
        }

    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }
}
