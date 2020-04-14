package fi.himmi;


import java.util.Scanner;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Biotietokanta {
    
    private List<Kayttaja>kayttajat;
    private List<Laji>lajit;
    
    public Biotietokanta() {
        
        this.kayttajat=new ArrayList<>();
        this.lajit=new ArrayList<>();
    }
    public int luoTunnus(String tunnus, String salasana) {

        if (salasana.length()<6) {
            // System.out.println("Salasana on liian lyhyt. Keksi toinen salasana.");
            return -1;
        }

        if (this.kayttajat.isEmpty()) {
            Kayttaja uusiKayttaja = new Kayttaja(tunnus, salasana);
            // System.out.println("Käyttäjätunnus on luotu. Kirjaudu sisään.");
            this.kayttajat.add(uusiKayttaja);
            return 1;
        }
        else if (!this.kayttajat.isEmpty()) {
            int onnistuuko=1;
            for (Kayttaja kayttajatunnus : this.kayttajat) {
                if (kayttajatunnus.getTunnus().equals(tunnus)) {
                    // System.out.println("Käyttäjätunnus on varattu. Keksi uusi käyttäjätunnus.");
                    onnistuuko=0;
                    return 0;
                }
            }
            if (onnistuuko==1) {
                Kayttaja uusiKayttaja = new Kayttaja(tunnus, salasana);
                // System.out.println("Käyttäjätunnus on luotu.");
                this.kayttajat.add(uusiKayttaja);
                return 1;
            }
        }
        return -2;
    }
    
    public int kirjauduSisaan(String tunnus, String salasana) {
        int onnistuuko=0;
        if (!this.kayttajat.isEmpty()) {
            for (Kayttaja kayttajatunnus : this.kayttajat) {
                if (kayttajatunnus.getTunnus().equals(tunnus)) {
                
                    if (kayttajatunnus.getSalasana().equals(salasana)) {
                        //vaihda näkymää
                        // System.out.println("Kirjautuminen onnistui");
                        onnistuuko=1;
                        return 1;
                    } else {
                        // System.out.println("Salasana on väärin.");
                        onnistuuko=0;
                        return 0;
                    }
                }
            }
        }
        if (onnistuuko==0) {            
            // System.out.println("Käyttäjätunnus on väärin.");
            return -1;
        }
        return -2;
    }
    
//    public void lisaa() throws SQLException {
//        System.out.println("Syötä lajin nimi");
//        String laji = this.lukija.nextLine();
//        System.out.println("Syötä sekvenssi");
//        String sekvenssi = this.lukija.nextLine();
//
//        Laji uusiLaji = new Laji(sekvenssi, laji);
//
//        this.tietokanta.create(uusiLaji);
//        System.out.println("Laji lisattiin");
//
//    }
    public int add(String sekvenssi, String nimi) {
        sekvenssi=sekvenssi.toLowerCase();
        
        for (String s: sekvenssi.split("")) {
            
            if (s.equals("a") || s.equals("t") || s.equals("c") || s.equals("g") || s.equals("\n")) {
            }
            else {
                // System.out.println("Sekvenssi ei ole aito");
                return 0;
            }
        }        
        if (!this.lajit.isEmpty()) {
            int exists=0;            
            for (Laji laji : this.lajit) {
                if (laji.getLaji().equals(nimi)) {
                    System.out.println("Laji on jo listassa");
                    return -1;
                }                
            }
            if (exists==0) {
                Laji uusiLaji = new Laji(sekvenssi, nimi);
                this.lajit.add(uusiLaji);
                System.out.println("Laji lisättiin");
                return 1;
            }
        }
        else {
            Laji uusiLaji = new Laji(sekvenssi, nimi);
            this.lajit.add(uusiLaji);
            System.out.println("Laji lisättiin");
            return 1;
        }
        return -2;
    }
    public List search(String sekvenssi) {
        List<String>matches = new ArrayList<>();
        sekvenssi=sekvenssi.toLowerCase();        
        for (Laji laji: this.lajit) {
            if (laji.getData().contains(sekvenssi)) {
                matches.add(laji.getLaji());
            }
        }
        return matches;
    }
}
