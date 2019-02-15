/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author myriam.fort
 */
public class Message implements Serializable{
    private String sender;
    private String content;
    public Message(String s, String c)
    {
        sender=s;
        content=c;
    }

    public Message(BigDecimal bid, String contenu, String emetteur, Date dateEnvoi) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public String toString()
    {
        return content+ " envoyé par "+sender;
    }
    public void setId(String s)
    {
        sender=s;
    }


}
