
import java.util.Scanner;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Biotietokanta {
    
    private Scanner lukija;
    private Tietokanta tietokanta;
    private List<Kayttaja>kayttajat;
    
    public Biotietokanta(Scanner lukija, Tietokanta tietokanta) {
        this.lukija=lukija;
        this.tietokanta=tietokanta;
        this.kayttajat=new ArrayList<>();
    }
    public void luoTunnus(String tunnus, String salasana) {
        
        if (salasana.length()<6) {
            System.out.println("Salasana on liian lyhyt. Keksi toinen salasana.");
        }

        if (this.kayttajat.isEmpty()) {
            Kayttaja uusiKayttaja = new Kayttaja(tunnus, salasana);
            System.out.println("Käyttäjätunnus on luotu. Kirjaudu sisään.");
            this.kayttajat.add(uusiKayttaja);
        }
        else if (!this.kayttajat.isEmpty()) {
            int onko=0;
            for (Kayttaja kayttajatunnus : this.kayttajat) {
                if (kayttajatunnus.getTunnus().equals(tunnus)) {
                    System.out.println("Käyttäjätunnus on varattu. Keksi uusi käyttäjätunnus.");
                    onko=1;
                    break;
                }
            }
            if (onko==0) {
                Kayttaja uusiKayttaja = new Kayttaja(tunnus, salasana);
                System.out.println("Käyttäjätunnus on luotu. Kirjaudu sisään.");
                this.kayttajat.add(uusiKayttaja);
            }
        }
    }
    
    public void kirjauduSisaan(String tunnus, String salasana) {
        int onko=0;
        if (!this.kayttajat.isEmpty()) {
            for (Kayttaja kayttajatunnus : this.kayttajat) {
                if (kayttajatunnus.getTunnus().equals(tunnus)) {
                    onko=1;
                
                    if (kayttajatunnus.getSalasana().equals(salasana)) {
                        //vaihda näkymää
                        System.out.println("Kirjautuminen onnistui");
                        break;
                    } else {
                        System.out.println("Salasana on väärin.");
                        break;
                    }
                }
            }
        }
        if (onko==0) {            
            System.out.println("Käyttäjätunnus on väärin.");
        }      
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
