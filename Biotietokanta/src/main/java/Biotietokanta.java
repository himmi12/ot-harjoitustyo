
import java.util.Scanner;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Biotietokanta {
    
    private List<Kayttaja>kayttajat;
    
    public Biotietokanta() {
        
        this.kayttajat=new ArrayList<>();
    }
    public boolean luoTunnus(String tunnus, String salasana) {
        
        if (salasana.length()<6) {
            System.out.println("Salasana on liian lyhyt. Keksi toinen salasana.");
            return false;
        }

        if (this.kayttajat.isEmpty()) {
            Kayttaja uusiKayttaja = new Kayttaja(tunnus, salasana);
            System.out.println("Käyttäjätunnus on luotu. Kirjaudu sisään.");
            this.kayttajat.add(uusiKayttaja);
            return true;
        }
        else if (!this.kayttajat.isEmpty()) {
            int onnistuuko=1;
            for (Kayttaja kayttajatunnus : this.kayttajat) {
                if (kayttajatunnus.getTunnus().equals(tunnus)) {
                    System.out.println("Käyttäjätunnus on varattu. Keksi uusi käyttäjätunnus.");
                    onnistuuko=0;
                    return false;
                }
            }
            if (onnistuuko==1) {
                Kayttaja uusiKayttaja = new Kayttaja(tunnus, salasana);
                System.out.println("Käyttäjätunnus on luotu. Kirjaudu sisään.");
                this.kayttajat.add(uusiKayttaja);
                return true;
            }
        }
        return false;
    }
    
    public boolean kirjauduSisaan(String tunnus, String salasana) {
        int onnistuuko=0;
        if (!this.kayttajat.isEmpty()) {
            for (Kayttaja kayttajatunnus : this.kayttajat) {
                if (kayttajatunnus.getTunnus().equals(tunnus)) {
                
                    if (kayttajatunnus.getSalasana().equals(salasana)) {
                        //vaihda näkymää
                        System.out.println("Kirjautuminen onnistui");
                        onnistuuko=1;
                        return true;
                    } else {
                        System.out.println("Salasana on väärin.");
                        onnistuuko=0;
                        return false;
                    }
                }
            }
        }
        if (onnistuuko==0) {            
            System.out.println("Käyttäjätunnus on väärin.");
            return false;
        }
        return false;
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

}
