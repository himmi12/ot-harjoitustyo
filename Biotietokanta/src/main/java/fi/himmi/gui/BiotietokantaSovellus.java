package fi.himmi.gui;

import fi.himmi.logics.Biotietokanta;
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
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;

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
        bioDb.fromDbToSeq();
        bioDb.fromDbToUser();
        this.pw = "";
        

        TextArea sequence = new TextArea();        
        Button signIn = new Button("Sign in");
        Button signUp = new Button("Sign up");
        Button srch = new Button("Search");
        Label list = new Label("");
        list.setTextFill(Color.WHITE);
        Label intro = new Label("Enter a FASTA sequence");
        intro.setTextFill(Color.WHITE);
        Image image = new Image("file:fluorisoiva.png");
        BackgroundImage bimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background bg = new Background(bimage);
        
        VBox pubView = new VBox();        
        pubView.setSpacing(10);
        pubView.getChildren().addAll(intro, sequence, srch, list, signIn, signUp);
        
        HBox pubLayout = new HBox();        
        pubLayout.getChildren().addAll(pubView);
        pubLayout.setBackground(bg);
        

        
        TextField username = new TextField();
        username.setMaxWidth(150);
        PasswordField password = new PasswordField();
        password.setMaxWidth(150);
        Label user = new Label("Username");
        Label passw = new Label("Password");
        user.setTextFill(Color.WHITE);
        passw.setTextFill(Color.WHITE);
        Button create = new Button("Create an account");
        Label info = new Label("");
        info.setTextFill(Color.WHITE);        
        Label accountExists = new Label("Already have an account?");
        accountExists.setTextFill(Color.WHITE);
        Button toSigningIn = new Button("Sign in!");
        
        HBox changeTheView = new HBox();
        changeTheView.setSpacing(10);
        changeTheView.getChildren().addAll(accountExists, toSigningIn);
        
        VBox signingUp = new VBox();        
        signingUp.setSpacing(10);
        signingUp.getChildren().addAll(user, username, passw, password, create, info, changeTheView);
        
        signingUp.setBackground(bg);
        

        
        TextField u = new TextField();
        u.setMaxWidth(150);
        PasswordField p = new PasswordField();
        p.setMaxWidth(150);
        Label userN = new Label("Username");
        userN.setTextFill(Color.WHITE);
        Label passW = new Label("Password");
        passW.setTextFill(Color.WHITE);
        Button toPrivateView = new Button("Sign in!");
        Button back = new Button("Back");
        Label msg = new Label("");
        msg.setTextFill(Color.WHITE);
        
        VBox signingIn = new VBox();
        
        signingIn.setSpacing(10);
        signingIn.getChildren().addAll(userN, u, passW, p, msg, toPrivateView, back);
        
        signingIn.setBackground(bg);
        

        
        TextArea seq = new TextArea();
        Button logout = new Button("Log out");
        Button search = new Button("Search");
        Label result = new Label("");
        result.setTextFill(Color.WHITE);
        Label enter = new Label("Enter a FASTA sequence");
        enter.setTextFill(Color.WHITE);
        
        VBox searchArea = new VBox();
        searchArea.getChildren().addAll(enter, seq, search, result);
        
        TextArea addSeq = new TextArea();
        TextField addName = new TextField();
        addName.setMaxWidth(300);
        Button addButton = new Button("Add");
        Label message = new Label("");
        message.setTextFill(Color.WHITE);
        
        VBox add = new VBox();
        Label addS = new Label("Add a FASTA sequence");
        addS.setTextFill(Color.WHITE);
        Label addSN = new Label("Add the scientific name of the species");
        addSN.setTextFill(Color.WHITE);
        add.getChildren().addAll(addS, addSeq, addSN, addName, addButton, message);
        
        BorderPane priLayout = new BorderPane();
        priLayout.setLeft(searchArea);
        priLayout.setBottom(logout);
        priLayout.setRight(add);
        
        priLayout.setBackground(bg);
        
        
        Scene pub = new Scene(pubLayout, 1000, 600);
        Scene newAccount = new Scene(signingUp, 1000, 600);
        Scene account = new Scene(signingIn, 1000, 600);
        Scene priv = new Scene(priLayout, 1000, 600);
        
        
        signUp.setOnAction((event) -> {
            sequence.setText("");
            list.setText("");
            window.setScene(newAccount);
        });
        signIn.setOnAction((event) -> {
            sequence.setText("");
            list.setText("");
            window.setScene(account);
        });

        toPrivateView.setOnAction((event)-> {
            this.un = u.getText().trim();
            this.pw = p.getText();            
            int reply = bioDb.kirjauduSisaan(this.un, this.pw);
            if (reply == 1) {
                u.setText("");
                p.setText("");
                window.setScene(priv);
            }
            else if (reply == 0) {
                msg.setText("Incorrect password");
            } 
            else if (reply == -1) {
                msg.setText("Incorrect username");
            }
        });
        create.setOnAction((event) -> {
            this.un = username.getText().trim();
            this.pw = password.getText(); 
            int reply = bioDb.luoTunnus(this.un, this.pw);
            if (reply == 1) {
                username.setText("");
                password.setText("");
                window.setScene(priv);
            } else if (reply == -1) {
                info.setText("Password is too short.");
            } else if (reply == 0) {
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
            result.setText("");
            message.setText("");
            seq.setText("");
            window.setScene(pub);
        });
        addButton.setOnAction((event) -> {
            this.sequence = addSeq.getText().trim();
            this.species = addName.getText().trim();
            
            int reply = bioDb.add(this.sequence, this.species);
            if (reply == 1) {
                message.setText("Succeeded");
                addSeq.setText("");
                addName.setText("");
            }
            else if (reply == -1) {
                message.setText(this.species+" exists in genome browser");
            }
            else if (reply == 0) {
                message.setText("Could not add the sequence. Only A,T,C,G accepted.");
            }
            else if (reply == -2) {
                message.setText("Add the binomial name of the species. For instance Homo sapiens");
            }
        });
        search.setOnAction((event) -> {
            String seqData = seq.getText().trim();
            List results = bioDb.search(seqData);
            
            if (results.isEmpty()) {
                result.setText("No matching results found.");
            }
            else {
                String species = "";
                for (Object s: results) {
                    species = species + "\n" + s;
                }
                result.setText(species);
            }
        });
        srch.setOnAction((event) -> {
            String seqData = sequence.getText().trim();
            List results = bioDb.search(seqData);
            
            if (results.isEmpty()) {
                list.setText("No matching results found.");
            }
            else {
                String species = "";
                for (Object s: results) {
                    species = species + "\n" + s;
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
