/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import common.Message;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author myriam.fort
 */
public class ClientSend implements Runnable {

    private Socket socket;
    private ObjectOutputStream out;

    public ClientSend(Socket soc, ObjectOutputStream o) {
        socket = soc;
        out = o;
    }

    public void run() {
        try {

            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.print("Votre message >> ");
                String m = sc.nextLine();
                Message mess = new Message("client", m);
                out.writeObject(mess);

                out.flush();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
