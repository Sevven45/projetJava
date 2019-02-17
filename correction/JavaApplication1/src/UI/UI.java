/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Hibernate.Message;
import client.Client;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import client.ClientSend.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javafx.application.Platform;
import javafx.stage.WindowEvent;



    public class UI extends Application {
        static String Nom;
        static Client c;
        static ArrayList<Message> feed= new ArrayList<Message>();
        
        ListView<Message> listView = new ListView<Message>();

        public static void Appli(String nom,Client c) {
            UI.Nom = nom;
            UI.c = c;
            Application.launch();
        }

   
        

        @Override
        public void start(Stage primaryStage) {
            String M;
           
            initListView(listView);
            Calendar cal = Calendar.getInstance();
            Calendar myCal = Calendar.getInstance();
            myCal.set(Calendar.YEAR, 2019);
            myCal.set(Calendar.MONTH, 01);
            myCal.set(Calendar.DAY_OF_MONTH, 01);
            Date theDate = myCal.getTime(); 
            Message mess = new Message("didier","didider",theDate);
            feed.add(mess);
          
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 550, 350, Color.WHITE);

            GridPane gridpane = new GridPane();
            gridpane.setPadding(new Insets(5));
            gridpane.setHgap(5);
            gridpane.setVgap(5);



            Label fNameLbl = new Label(Nom);
            TextArea textArea = new TextArea();
            double height = 80; //making a variable called height with a value 400
            double width = 400;  //making a variable called height with a value 300


            textArea.setPrefHeight(height);
            textArea.setPrefWidth(width);

            Button sendButt = new Button("Send");
            sendButt.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    String M = textArea.getText();
                    c.sendMsg(M);
                }
            });
            
            
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
                }
            });

            // Pseudo label
            GridPane.setHalignment(fNameLbl, HPos.RIGHT);
            gridpane.add(fNameLbl, 0, 0);

            // Text field
            GridPane.setHalignment(textArea, HPos.LEFT);
            gridpane.add(textArea, 1, 0);

            // Send button
            GridPane.setHalignment(sendButt, HPos.RIGHT);
            gridpane.add(sendButt, 2, 0);

            //listview
            initListView(listView);
            gridpane.add(listView, 1, 3);
            
            
            root.setCenter(gridpane);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        
        public static void initMessList(Message mess){
            feed.add(mess);
        }
        public static void initListView( ListView listView){
            for (Message Mess : feed) {
                listView.getItems().add(Mess.getContenu()+"---"+Mess.getEmetteur());
            }
        }
        
         public void addMessList(Message mess){
            feed.add(mess);
            initListView(listView);
        }
    }
