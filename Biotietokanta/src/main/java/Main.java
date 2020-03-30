
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author himmi
 */
public class Main {
    
    public static void main(String[] args) {
        String tietokannanPolku = "";
        if (args.length > 0) {
            tietokannanPolku = args[0];
        }
 
        Tietokanta tietokanta = new Tietokanta(tietokannanPolku);
        Scanner lukija = new Scanner(System.in);
 
        new Biotietokanta(lukija, tietokanta).kaynnista();
    }
}
