/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.himmi.gui;

import java.sql.SQLException;

/**
 *
 * @author himmi
 */
public class Main {
    
    public static void main(String[] args) throws SQLException {
        
//        String tietokannanPolku = "jdbc:h2:./sekvenssit";
//        if (args.length > 0) {
//            tietokannanPolku = args[0];
//        }
//
//        LajiDaoClass tietokanta = new LajiDaoClass(tietokannanPolku);

        BiotietokantaSovellus.main(args);          
    }
}
