/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import UI.UI;
import Hibernate.Message;
import static client.MainClient.pseudo;
import java.io.*;
import java.net.Socket;

/**
 *
 * @author myriam.fort
 */
public class Client {
private String adresse;
private int port;
private Socket socket;
private ObjectInputStream in;
private ObjectOutputStream out;
String pseudo = "Defaults";
       
public Client(String ad, int p, String pseudo) throws IOException
{
    adresse=ad;
    port=p;
    socket=new Socket(ad, p);
    out = new ObjectOutputStream(socket.getOutputStream());
    
    Thread threadClientR =new Thread(new ClientReceive(this, socket));
    threadClientR.start();
}

public void sendMsg(String m){
    Thread threadClientS =new Thread(new ClientSend(socket, out, pseudo, m));
    threadClientS.start();
}

public void messageReceived(Message mess){
    System.out.println(mess.toString());
}

public void disconnectedServer() throws IOException{
    out.close();
    socket.close();
    if (in!=null)
        in.close();
    System.exit(0);
}

public String getPseudo(){
    return this.pseudo;
}
}
