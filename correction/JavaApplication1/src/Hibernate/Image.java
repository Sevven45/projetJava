package Hibernate;
// Generated 5 f�vr. 2019 11:18:07 by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * Image generated by hbm2java
 */
public class Image  implements java.io.Serializable {


     private BigDecimal id;
     private String urlImg;
     private String idMessage;

    public Image() {
    }

	
    public Image(BigDecimal id) {
        this.id = id;
    }
    public Image(BigDecimal id, String urlImg, String idMessage) {
       this.id = id;
       this.urlImg = urlImg;
       this.idMessage = idMessage;
    }
   
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    public String getUrlImg() {
        return this.urlImg;
    }
    
    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }
    public String getIdMessage() {
        return this.idMessage;
    }
    
    public void setIdMessage(String idMessage) {
        this.idMessage = idMessage;
    }




}


