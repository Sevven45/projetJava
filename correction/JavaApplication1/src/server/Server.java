/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import Gestion_BDD.Gestion_BDD;
import Hibernate.Message;
import UI.UI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
        this.clients.add(newClient);
    }
    
    public void broadcastMessage() throws IOException {
        //Message leMessage = Gestion_BDD.queryLastMessag();
        Message leMessage = testMess();
        for (ConnectedClient client : clients)
        {
            UI.initMessList(leMessage);
        }
    }
    
    public void broadcastAllMessages() throws IOException {
        System.out.println("broadcastAll");
        //List<Message> lesMessages = Gestion_BDD.queryMessage();
        List<Message> lesMessages= testListMess();
        System.out.println(lesMessages);


        for (ConnectedClient client : clients) {
            System.out.println("Wola");
            for(Message leMessage : lesMessages){
                System.out.println("Et un de plus!!!");
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
    
    
    public List<Message> testListMess(){
           List<Message> lesMessages = new ArrayList<Message>();
         Calendar myCal = Calendar.getInstance();
                myCal.set(Calendar.YEAR, 2019);
                myCal.set(Calendar.MONTH, 01);
                myCal.set(Calendar.DAY_OF_MONTH, 01);
                Date theDate = myCal.getTime();
        Message mess01 = new Message("Message numero 30015","Jacques Berger",theDate);
        Message mess02 = new Message("JE suis un hardcore gamer","Francis Port",theDate);
        lesMessages.add(mess01);
        lesMessages.add(mess02);
        return lesMessages;
    }
    
    public Message testMess(){
         Calendar myCal = Calendar.getInstance();
                myCal.set(Calendar.YEAR, 2019);
                myCal.set(Calendar.MONTH, 01);
                myCal.set(Calendar.DAY_OF_MONTH, 01);
                Date theDate = myCal.getTime();
        Message mess = new Message("Message numero 30015","Jacques Berger",theDate);
        return mess;
    }
}
