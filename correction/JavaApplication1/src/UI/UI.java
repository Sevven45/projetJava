/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

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



    public class UI extends Application {

        public static void Appli() {
            Application.launch();
        }

        @Override
        public void start(Stage primaryStage) {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 550, 350, Color.WHITE);

            GridPane gridpane = new GridPane();
            gridpane.setPadding(new Insets(5));
            gridpane.setHgap(5);
            gridpane.setVgap(5);



            Label fNameLbl = new Label("Pseudo");
            TextArea textArea = new TextArea();
            double height = 80; //making a variable called height with a value 400
            double width = 400;  //making a variable called height with a value 300


            textArea.setPrefHeight(height);
            textArea.setPrefWidth(width);

            Button sendButt = new Button("Send");
            sendButt.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    System.out.println(textArea.getText());
                }
            });

            // First name label
            GridPane.setHalignment(fNameLbl, HPos.RIGHT);
            gridpane.add(fNameLbl, 0, 0);



            // First name field
            GridPane.setHalignment(textArea, HPos.LEFT);
            gridpane.add(textArea, 1, 0);

            // Save button
            GridPane.setHalignment(sendButt, HPos.RIGHT);
            gridpane.add(sendButt, 2, 0);

            //scrollpane
            ScrollPane s1 = new ScrollPane();
            s1.setPrefSize(400, 250);
            gridpane.add(s1, 1, 3);


            root.setCenter(gridpane);
            primaryStage.setScene(scene);
            primaryStage.show();
        }

    }
