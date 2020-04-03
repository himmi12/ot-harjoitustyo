

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
    
    @Override
    public void start(Stage window) throws Exception {
        Biotietokanta bioDb = new Biotietokanta();
        
        //public view
        TextArea sequence = new TextArea();        
        Button signIn = new Button("Sign in");
        Button signUp = new Button("Sign up");
        
        VBox pubView = new VBox();        
        pubView.setSpacing(20);
        pubView.getChildren().addAll(new Label("Enter FASTA sequence(s)"), sequence, signIn, signUp);
        
        HBox pubLayout = new HBox();        
        pubLayout.getChildren().addAll(pubView);
        
        //Sign up -view
        
        TextField username = new TextField();
        TextField password = new TextField();
        Label user = new Label("Username");
        Label passw = new Label("Password");
        Button create = new Button("Create an account");
        
        Label accountExists = new Label("Already have an account?");
        Button toSigningIn = new Button("Sign in!");
        
        HBox changeTheView = new HBox();
        changeTheView.setSpacing(10);
        changeTheView.getChildren().addAll(accountExists, toSigningIn);
        
        VBox signingUp = new VBox();
        
        signingUp.setSpacing(10);
        signingUp.getChildren().addAll(user, username, passw, password, create, changeTheView);
        
        // sign in -view
        
        TextField u = new TextField();
        TextField p = new TextField();
        Label userN = new Label("Username");
        Label passW = new Label("Password");
        Button toPrivateView = new Button("Sign in!");
        Button back = new Button("Back");
        
        VBox signingIn = new VBox();
        
        signingIn.setSpacing(10);
        signingIn.getChildren().addAll(userN, u, passW, p, toPrivateView, back);
        
        // private view
        
        TextArea seq = new TextArea();
        Button logout = new Button("Log out");
        Button search = new Button("Search");
        
        VBox searchArea = new VBox();
        searchArea.getChildren().addAll(new Label("Enter FASTA sequence(s)"), seq, search);
        
        BorderPane priLayout = new BorderPane();
        priLayout.setLeft(searchArea);
        priLayout.setRight(logout);
        
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
            boolean reply=bioDb.kirjauduSisaan(this.un, this.pw);
            if (reply==true) {
                window.setScene(priv);
            }
        });
        create.setOnAction((event) -> {
            this.un=username.getText();
            this.pw=password.getText();            
            boolean reply=bioDb.luoTunnus(this.un, this.pw);
            if (reply==true) {
                window.setScene(priv);
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
        
        window.setScene(pub);
        window.show();
    }
    public static void main(String[] args) {
        launch(BiotietokantaSovellus.class);
    }
    
}
