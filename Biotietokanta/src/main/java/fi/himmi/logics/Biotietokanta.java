package fi.himmi.logics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.util.Arrays;


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
        String onko = searchOne("kayttajat.txt", tunnus, 1); // numero 1 kertoo että haetaan käyttäjätunnusta
        if (onko.equals(tunnus)) {
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
        
        String onnistuuko = searchTwo("kayttajat.txt", tunnus, salasana, 1); 
        
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
        String onko = searchOne("sekvenssit.txt", nimi, 2); 
        if (onko.equals(nimi)) {
            return 0; //laji on jo lisätty tietokantaan
        } else {
            Laji uusiLaji = new Laji(sekvenssi, nimi);
            this.lajit.add(uusiLaji);
            addToDb("sekvenssit.txt", sekvenssi, nimi);
            return 1; //lisätään tietokantaan
        }
//        if (!this.lajit.isEmpty()) {            
//            for (Laji laji : this.lajit) {
//                if (laji.getLaji().equals(nimi)) { //laji on jo listassa
//                    return -1;
//                }                
//            }
//            Laji uusiLaji = new Laji(sekvenssi, nimi);
//                this.lajit.add(uusiLaji);
//                return 1;            
//        } else {
//            Laji uusiLaji = new Laji(sekvenssi, nimi);
//            this.lajit.add(uusiLaji);
//            return 1;
//        }
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
            System.out.println("Tunnus lisättiin!");
            bufferedWriter.close();
        } catch (IOException e) {

        }
    }
    /**
     * Hakee valitusta tekstitiedostosta valittuja tietoja. 
     * @param tiedosto
     * @param tunnusSekvenssi
     * @param salasanaLaji
     * @param tVaiS (määrittelee, etsitäänkö tunnusta vai tietyn tunnuksen salasanaa)
     * @return merkkijono, joka kertoo, onnistuiko haku tai miksi ei onnistunut
     */
    public String searchTwo(String tiedosto, String tunnusSekvenssi, String salasanaLaji, int tVaiS) {
        try {
            ArrayList<String>haettavat = new ArrayList<>();
            ArrayList<String>parilliset = new ArrayList<>();
            ArrayList<String>parittomat = new ArrayList<>();
            FileReader reader = new FileReader(tiedosto);
            BufferedReader bufferedReader = new BufferedReader(reader); 
            String line;
            Boolean tunnusOikein = false;
            while ((line = bufferedReader.readLine()) != null) {
                haettavat.addAll(Arrays.asList(line.split(":")));
            }
            int i = 0;
            while (i < haettavat.size()) {
                if (i % 2 != 0) {
                    parittomat.add(haettavat.get(i));
                } else {
                    parilliset.add(haettavat.get(i));
                }
                i++;
            }
            if (tVaiS == 1) {
                if (searchOne(tiedosto, tunnusSekvenssi, tVaiS).equals(tunnusSekvenssi)) {
                    int salasananIndeksi = parilliset.indexOf(tunnusSekvenssi);
                    if (parittomat.get(salasananIndeksi).equals(salasanaLaji)) {
                        return "Kirjautuminen onnistui";
                    }
                    return "Salasana väärin";
                }
                return "Käyttäjätunnusta ei löytynyt";
            }
            if (tVaiS == 2) {
                
            }
//                for (String k: line.split(":")) { 
//                    if (tVaiS==1) {
//                        if (searchOne(tiedosto, tunnusSekvenssi, tVaiS).equals(tunnusSekvenssi)) {
//
//                        }
//                    }
//                    if (s.equals(tunnusSekvenssi)) {
//                        if (tVaiS == 1) {
//                            reader.close();
//                            return "Löytyi"; // käyttäjä tai sekvenssi löytyi tietokannasta
//                        } else if (tVaiS == 2) {
//                            tunnusOikein = true;
//                        }                        
//                    } else if (s.equals(salasanaLaji) && tunnusOikein == true && tVaiS == 2) {
//                            reader.close();
//                            return "Kirjautuminen onnistui";
//                    } else if (!s.equals(salasanaLaji) && tunnusOikein == true && tVaiS == 2) {
//                        return "Salasana väärin"; //käyttäjä löytyi mutta salasana väärin
//                    }                    
//                }
            
            reader.close();
            return "Käyttäjätunnusta ei löytynyt"; //käyttäjätunnusta ei löytnyt
          
        } catch (IOException e) {

        }
        return "Haku ei toiminut";
    }
    public String searchOne(String tiedosto, String haettava, int tunnusVaiSekvenssi) {
        try {
            ArrayList<String>haettavat = new ArrayList<>();
            ArrayList<String>parilliset = new ArrayList<>();
            List<String>parittomat = new ArrayList<>();
            FileReader reader = new FileReader(tiedosto);
            BufferedReader bufferedReader = new BufferedReader(reader); 
            String line;
            while ((line = bufferedReader.readLine()) != null) { 
                haettavat.addAll(Arrays.asList(line.split(":")));
            }
            int i = 0;
            while (i < haettavat.size()) {
                if (i % 2 != 0) {
                    parittomat.add(haettavat.get(i));
                } else {
                    parilliset.add(haettavat.get(i));
                }
                i++;
            }
            if (tunnusVaiSekvenssi == 1) { //haetaan käyttäjätunnusta
                for (String s : parilliset) {
                    if (s.equals(haettava)) {
                        reader.close();
                        return haettava; //tunnus on olemassa
                    }
                }
                reader.close();
                return ""; //tunnusta ei ole
            } else if (tunnusVaiSekvenssi == 2) { //haetaan lajia
                for (String s : parittomat) {
                    if (s.equals(haettava)) {
                        reader.close();
                        return haettava; //laji löytyy
                    }
                }
                reader.close();
                return "";
            }
            
        }catch (IOException e) {
            
        }
        return "";
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
