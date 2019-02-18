/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import Hibernate.Util;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Hibernate.Message;

/**
 *
 * @author Charles
 */
public class TestAddMessage {
    
    public static void main(String[] args) {
        /*Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();
        String strDateFormat = "dd-mm-yyyy";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(now);
        System.out.println("Current date :" + now);*/
        Date now = new Date();
        System.out.println(now.toString());
        
        Message m = new Message("Test1", "Trapslight",now );
        Session session = Util.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        System.out.println("Fin test");
        session.save(m);
        tx.commit();

        session.close();



    }
}
