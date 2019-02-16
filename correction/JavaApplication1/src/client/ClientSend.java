/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import Hibernate.Message;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

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
                
                Calendar cal = Calendar.getInstance();
                Calendar myCal = Calendar.getInstance();
                myCal.set(Calendar.YEAR, 2019);
                myCal.set(Calendar.MONTH, 01);
                myCal.set(Calendar.DAY_OF_MONTH, 01);
                Date theDate = myCal.getTime(); 
                Message mess = new Message("client",m, theDate);
                
                out.writeObject(mess);
                out.flush();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
