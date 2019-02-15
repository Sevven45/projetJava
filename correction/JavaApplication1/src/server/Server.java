/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import Gestion_BDD.Gestion_BDD;
import Hibernate.Message;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
             Calendar cal = Calendar.getInstance();
                Calendar myCal = Calendar.getInstance();
                myCal.set(Calendar.YEAR, 2019);
                myCal.set(Calendar.MONTH, 01);
                myCal.set(Calendar.DAY_OF_MONTH, 01);
                Date theDate = myCal.getTime();
            client.sendMessage(new Message("Le client " + newClient.getId() + " vient de se connecter","server", theDate));
        }
        this.clients.add(newClient);

    }
    
    public void broadcastMessage() throws IOException {
        Message leMessage = Gestion_BDD.queryLastMessag();
        for (ConnectedClient client : clients)
        {
            client.sendMessage(leMessage);
        }
    }
    
    public void broadcastAllMessages() throws IOException {
        List<Message> lesMessages = Gestion_BDD.queryMessage();
        for (ConnectedClient client : clients) {
            
            for(Message leMessage : lesMessages)
            {
                client.sendMessage(leMessage);
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
                Calendar myCal = Calendar.getInstance();
                myCal.set(Calendar.YEAR, 2019);
                myCal.set(Calendar.MONTH, 01);
                myCal.set(Calendar.DAY_OF_MONTH, 01);
                Date theDate = myCal.getTime();
            client.sendMessage(new Message("Le client "+ discClient.getId() + " nous a quitté", "server", theDate));
        }
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }
}
