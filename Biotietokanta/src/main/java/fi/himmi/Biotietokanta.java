package fi.himmi;


import java.util.Scanner;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Biotietokanta {
    
    private List<Kayttaja> kayttajat;
    private List<Laji> lajit;
    
    public Biotietokanta() {
        
        this.kayttajat = new ArrayList<>();
        this.lajit = new ArrayList<>();
    }
    public int luoTunnus(String tunnus, String salasana) {
        if (salasana.length() < 6) {
            return -1;
        } 
        if (this.kayttajat.isEmpty()) {
            Kayttaja uusiKayttaja = new Kayttaja(tunnus, salasana);
            this.kayttajat.add(uusiKayttaja);
            return 1;
        } else if (!this.kayttajat.isEmpty()) {
            for (Kayttaja kayttajatunnus : this.kayttajat) {
                if (kayttajatunnus.getTunnus().equals(tunnus)) {
                    return 0;
                }
            }
        }
        Kayttaja uusiKayttaja = new Kayttaja(tunnus, salasana);
        this.kayttajat.add(uusiKayttaja);
        return 1;
    }
    
    public int kirjauduSisaan(String tunnus, String salasana) {
        if (!this.kayttajat.isEmpty()) {
            for (Kayttaja kayttajatunnus : this.kayttajat) {
                if (kayttajatunnus.getTunnus().equals(tunnus)) {
                
                    if (kayttajatunnus.getSalasana().equals(salasana)) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        }            
        return -1;
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
        sekvenssi = sekvenssi.toLowerCase();        
        for (String s: sekvenssi.split("")) {            
            if (!s.equals("a") || !s.equals("t") || !s.equals("c") || !s.equals("g") || !s.equals("\n")) {
                return 0;
            }
        }        
        if (!this.lajit.isEmpty()) {            
            for (Laji laji : this.lajit) {
                if (laji.getLaji().equals(nimi)) { //laji on jo listassa
                    return -1;
                }                
            }
            Laji uusiLaji = new Laji(sekvenssi, nimi);
            this.lajit.add(uusiLaji);
            return 1;            
        } else {
            Laji uusiLaji = new Laji(sekvenssi, nimi);
            this.lajit.add(uusiLaji);
            return 1;
        }
    }
    public List search(String sekvenssi) {
        List<String> matches = new ArrayList<>();
        sekvenssi = sekvenssi.toLowerCase();        
        for (Laji laji: this.lajit) {
            if (laji.getData().contains(sekvenssi)) {
                matches.add(laji.getLaji());
            }
        }
        return matches;
    }
}
