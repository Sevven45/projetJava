/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestion_BDD;

import Hibernate.Util;
import Hibernate.Message;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author p1811833
 */
public class Gestion_BDD {
    
    public static Message queryLastMessag(){
        Session session = Util.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        Query query = session.createQuery("from Message");
        query.setMaxResults(1);
        Message last = (Message) query.uniqueResult();
        
        session.close();
        
        return last;
    }
    
    public static List<Message> queryMessage(){
        
        Session session = Util.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        Query query = session.createQuery("from Message");
        List<Message> lesMessages = query.list();
        
        session.close();
        
        return lesMessages;
    }
    
    public static void insertMessage(Message m){
        System.out.println("Function : InsertMessage");
        Session session = Util.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        String hql = "INSERT INTO MESSAGE("+m.getContenu()+","+m.getEmetteur()+","+ m.getDateEnvoi()+")";
        Query query = session.createQuery(hql);
        int result = query.executeUpdate();
        System.out.println("Rows affected: "+result);
        
        session.save(m);
        session.flush();
        tx.commit();
        session.close();
    }
}
