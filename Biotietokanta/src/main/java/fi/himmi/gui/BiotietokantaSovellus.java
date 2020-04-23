package fi.himmi.gui;

import fi.himmi.Biotietokanta;
import java.util.List;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author himmi
 */
public class BiotietokantaSovellus extends Application {
    
    private String un;
    private String pw;
    private String sequence;
    private String species;
    
    @Override
    public void start(Stage window) throws Exception {
        Biotietokanta bioDb = new Biotietokanta();
        
        //public view
        TextArea sequence = new TextArea();        
        Button signIn = new Button("Sign in");
        Button signUp = new Button("Sign up");
        Button srch = new Button("Search");
        Label list = new Label("");
        
        VBox pubView = new VBox();        
        pubView.setSpacing(10);
        pubView.getChildren().addAll(new Label("Enter FASTA sequence"), sequence, srch, list, signIn, signUp);
        
        HBox pubLayout = new HBox();        
        pubLayout.getChildren().addAll(pubView);
        
        //Sign up -view
        
        TextField username = new TextField();
        TextField password = new TextField();
        Label user = new Label("Username");
        Label passw = new Label("Password");
        Button create = new Button("Create an account");
        Label info = new Label("");
        
        Label accountExists = new Label("Already have an account?");
        Button toSigningIn = new Button("Sign in!");
        
        HBox changeTheView = new HBox();
        changeTheView.setSpacing(10);
        changeTheView.getChildren().addAll(accountExists, toSigningIn);
        
        VBox signingUp = new VBox();
        
        signingUp.setSpacing(10);
        signingUp.getChildren().addAll(user, username, passw, password, create, info, changeTheView);
        
        // sign in -view
        
        TextField u = new TextField();
        TextField p = new TextField();
        Label userN = new Label("Username");
        Label passW = new Label("Password");
        Button toPrivateView = new Button("Sign in!");
        Button back = new Button("Back");
        Label msg = new Label("");
        
        VBox signingIn = new VBox();
        
        signingIn.setSpacing(10);
        signingIn.getChildren().addAll(userN, u, passW, p, msg, toPrivateView, back);
        
        // private view
        
        TextArea seq = new TextArea();
        Button logout = new Button("Log out");
        Button search = new Button("Search");
        Label result = new Label("");
        
        VBox searchArea = new VBox();
        searchArea.getChildren().addAll(new Label("Enter FASTA sequence"), seq, search, result);
        
        TextArea addSeq = new TextArea();
        TextField addName = new TextField();
        Button addButton = new Button("Add");
        Label message = new Label("");
        
        VBox add = new VBox();
        add.getChildren().addAll(new Label("Add FASTA sequence"), addSeq, new Label("Add the scientific name of the species"), addName, addButton, message);
        
        BorderPane priLayout = new BorderPane();
        priLayout.setLeft(searchArea);
        priLayout.setBottom(logout);
        priLayout.setRight(add);
        
        Scene pub = new Scene(pubLayout);
        Scene newAccount = new Scene(signingUp);
        Scene account = new Scene(signingIn);
        Scene priv = new Scene(priLayout);
        
        signUp.setOnAction((event) -> {
            window.setScene(newAccount);
        });
        signIn.setOnAction((event) -> {
            window.setScene(account);
        });
        toPrivateView.setOnAction((event)-> {
            this.un=u.getText();
            this.pw=p.getText();            
            int reply=bioDb.kirjauduSisaan(this.un, this.pw);
            if (reply==1) {
                window.setScene(priv);
            }
            else if (reply==0) {
                msg.setText("Incorrect password");
            } 
            else if (reply==-1) {
                msg.setText("Incorrect username");
            }
        });
        create.setOnAction((event) -> {
            this.un=username.getText();
            this.pw=password.getText();            
            int reply=bioDb.luoTunnus(this.un, this.pw);
            if (reply==1) {
                window.setScene(priv);
            } else if (reply==-1) {
                info.setText("Password is too short.");
            } else if (reply==0) {
                info.setText("Username is already taken.");
            }
        });
        toSigningIn.setOnAction((event) -> {
            window.setScene(account);
        });
        back.setOnAction((event) -> {
            window.setScene(pub);
        });
        logout.setOnAction((event) -> {
            window.setScene(pub);
        });
        addButton.setOnAction((event) -> {
            this.sequence=addSeq.getText();
            this.species=addName.getText();
            
            int reply=bioDb.add(this.sequence, this.species);
            if (reply==1) {
                message.setText("Succeeded");
            }
            else if (reply==-1) {
                message.setText(this.species+" exists in genome browser");
            }
            else if (reply==0) {
                message.setText("Could not add the sequence. Only A,T,C,G accepted.");
            }
        });
        search.setOnAction((event) -> {
            String seqData = seq.getText();
            List results = bioDb.search(seqData);
            
            if (results.isEmpty()) {
                result.setText("No matching results found.");
            }
            else {
                String species="";
                for (Object s: results) {
                    species=species+"\n"+s;
                }
                result.setText(species);
            }
        });
        srch.setOnAction((event) -> {
            String seqData = sequence.getText();
            List results = bioDb.search(seqData);
            
            if (results.isEmpty()) {
                list.setText("No matching results found.");
            }
            else {
                String species="";
                for (Object s: results) {
                    species=species+"\n"+s;
                }
                list.setText(species);
            }
        });
        
        window.setScene(pub);
        window.show();
    }
    public static void main(String[] args) {
        launch(BiotietokantaSovellus.class);
        
    }    
}
