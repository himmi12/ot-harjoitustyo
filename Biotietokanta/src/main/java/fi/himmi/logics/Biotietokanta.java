package fi.himmi.logics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;


public class Biotietokanta {
    
    private List<Kayttaja> kayttajat;
    private List<Laji> lajit;
    
    public Biotietokanta() {
        
        this.kayttajat = new ArrayList<>();
        this.lajit = new ArrayList<>();
    }
    
    /**
     * Luodaan käyttäjätunnus, jos luotava tunnus täyttää tunnuksen luomisen kriteerit.
     * @param tunnus
     * @param salasana
     * @return -1, 0 tai 1 
     */
    
    public int luoTunnus(String tunnus, String salasana) {
        if (salasana.length() < 6) {
            return -1; // salasana liian lyhyt            
        }
        String onko = searchFromDb("kayttajat.txt", tunnus, salasana, 1); // numero 1 kertoo että haetaan käyttäjätunnusta
        if (onko.equals("Tunnus löytyi")) {
            return 0; //tunnus on varattu
        } else {
            Kayttaja uusiKayttaja = new Kayttaja(tunnus, salasana);
            this.kayttajat.add(uusiKayttaja);
            addToDb("kayttajat.txt", tunnus, salasana);
            return 1; //lisätään tietokantaan
        }
    }
    /**
     * Tarkistetaan, onko tunnus tietokannassa ja vastaako syötteen salasana tietokannan salasanaa.
     * @param tunnus
     * @param salasana
     * @return -2,-1,0 tai 1 Riippuen siitä, mikä kirjautumisessa on epäonnistunut vai onko kirjautuminen 
     * onnistunut
     */
    
    public int kirjauduSisaan(String tunnus, String salasana) {
        
        String onnistuuko = searchFromDb("kayttajat.txt", tunnus, salasana, 2); 
        
        if (onnistuuko.equals("Salasana väärin")) {
            return 0; //käyttäjätunnus löytyi mutta salasana on väärin
        } else if (onnistuuko.equals("Käyttäjätunnusta ei löytynyt")) {
            return -1; //käyttäjätunnusta ei ole luotu
        } else if (onnistuuko.equals("Kirjautuminen onnistui")) {
            return 1; //kirjautuminen onnistuu
        } else {
            return -2; //jotain on mennyt pieleen
        }    
    }
    /**
     * Lisätään sekvenssi ja lajin nimi tietokantaan (toistaiseksi vielä listaan), jos kriteerit täyttyvät.
     * @param sekvenssi
     * @param nimi
     * @return -1,0 tai 1 riippuen siitä, mikä on mennyt pieleen vai onko lisääminen onnistunut
     */

    public int add(String sekvenssi, String nimi) {
        sekvenssi = sekvenssi.toLowerCase();        
        for (String s: sekvenssi.split("")) {            
            if (s.equals("a") || s.equals("t") || s.equals("c") || s.equals("g") || s.equals("\n")) {
            } else {
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
    /**
     * Haetaan lajin nimi tietokannasta sen perusteella sisältääkö tietokannan sekvenssi haettavan sekvenssin.
     * (Toistaiseksi toteutettu listana.)
     * @param sekvenssi
     * @return lista, sopivista lajien nimistä
     */
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
    /**
     * Lisätään valittuun tekstitiedostoon joko käyttäjätunnus ja salasana tai DNA-sekvenssi ja laji.
     * Tiedostoksi voi valita joko kayttajat tai sekvenssit -tiedoston
     * @param tiedosto
     * @param dataKayttajatunnus
     * @param lajiSalasana 
     */
    public void addToDb(String tiedosto, String dataKayttajatunnus, String lajiSalasana) {
        try {
            FileWriter writer = new FileWriter(tiedosto, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer); 
            bufferedWriter.newLine();
            bufferedWriter.write(dataKayttajatunnus + ":" + lajiSalasana); 
            bufferedWriter.close();
        } catch (IOException e) {

        }
    }
    /**
     * Hakee valitusta tekstitiedostosta valittuja tietoja. 
     * @param tiedosto
     * @param tunnus
     * @param salasana
     * @param ekaVaiToka (määrittelee, etsitäänkö tunnusta vai tietyn tunnuksen salasanaa)
     * @return merkkijono, joka kertoo, onnistuiko haku tai miksi ei onnistunut
     */
    public String searchFromDb(String tiedosto, String tunnus, String salasana, int ekaVaiToka) {
        try {
            FileReader reader = new FileReader(tiedosto);
            BufferedReader bufferedReader = new BufferedReader(reader); 
            String line;
            Boolean tunnusOikein = false;
            while ((line = bufferedReader.readLine()) != null) {
                for (String s: line.split(":")) {
                    if (s.equals(tunnus)) {
                        if (ekaVaiToka == 1) {
                            reader.close();
                            return "Tunnus löytyi"; // käyttäjä löytyi tietokannasta
                        } else if (ekaVaiToka == 2) {
                            tunnusOikein = true;
                        }                        
                    } else if (s.equals(salasana) && tunnusOikein == true && ekaVaiToka == 2) {
                            reader.close();
                            return "Kirjautuminen onnistui";
                    } else if (!s.equals(salasana) && tunnusOikein == true && ekaVaiToka == 2) {
                        return "Salasana väärin"; //käyttäjä löytyi mutta salasana väärin
                    }                    
                }
            }
            reader.close();
            return "Käyttäjätunnusta ei löytynyt"; //käyttäjätunnusta ei löytnyt
 
        } catch (IOException e) {

        }
        return "Haku ei toiminut";
    }
//    public void editDb(String file) {
//
//        FileInputStream fs = null;
//        InputStreamReader in = null;
//        BufferedReader br = null;
//
//        StringBuffer sb = new StringBuffer();
//
//        String textinLine;
//        try {
//            fs = new FileInputStream(file);
//            in = new InputStreamReader(fs);
//            br = new BufferedReader(in);
//
//            while (true) {
//                textinLine = br.readLine();
//                if (textinLine == null) {
//                    break;
//                }
//                sb.append(textinLine);
//            }
//            String textToEdit = br.readLine();
//            sb.replace(textToEdit, "");
//            
//        } catch (Exception e) {
//
//        }
//    }

}
