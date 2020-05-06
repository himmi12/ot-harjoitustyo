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
        if (!this.kayttajat.isEmpty()) {            
            for (Kayttaja k: kayttajat) {
                if (k.getTunnus().equals(tunnus)) { //tunnus on jo käytössä
                    return 0;
                }                
            }
            Kayttaja k = new Kayttaja(tunnus, salasana);
            this.kayttajat.add(k);
            addToDb("kayttajat.txt", tunnus, salasana);
            return 1;           
        } else {
            Kayttaja k = new Kayttaja(tunnus, salasana);
            this.kayttajat.add(k);
            addToDb("kayttajat.txt", tunnus, salasana);
            return 1;
        }
//        String onko = searchOne("kayttajat.txt", tunnus, 1); // numero 1 kertoo että haetaan käyttäjätunnusta
//        if (onko.equals(tunnus)) {
//            return 0; //tunnus on varattu
//        } else {
//            Kayttaja uusiKayttaja = new Kayttaja(tunnus, salasana);
//            this.kayttajat.add(uusiKayttaja);
//            addToDb("kayttajat.txt", tunnus, salasana);
//            return 1; //lisätään tietokantaan
//        }
    }
    /**
     * Tarkistetaan, onko tunnus tietokannassa ja vastaako syötteen salasana tietokannan salasanaa.
     * @param tunnus
     * @param salasana
     * @return -2,-1,0 tai 1 Riippuen siitä, mikä kirjautumisessa on epäonnistunut vai onko kirjautuminen 
     * onnistunut
     */
    
    public int kirjauduSisaan(String tunnus, String salasana) {
        for (Kayttaja k : kayttajat) {
            if (k.getTunnus().equals(tunnus)) {
                if (k.getSalasana().equals(salasana)) {
                    return 1; //onnistuu
                } else {
                    return 0; //salasana väärin
                }                
            }
        }
        return -1; //käyttäjätunnusta ei ole
//        String onnistuuko = searchTwo("kayttajat.txt", tunnus, salasana, 1); 
//        
//        if (onnistuuko.equals("Salasana väärin")) {
//            return 0; //käyttäjätunnus löytyi mutta salasana on väärin
//        } else if (onnistuuko.equals("Käyttäjätunnusta ei löytynyt")) {
//            return -1; //käyttäjätunnusta ei ole luotu
//        } else if (onnistuuko.equals("Kirjautuminen onnistui")) {
//            return 1; //kirjautuminen onnistuu
//        } else {
//            return -2; //jotain on mennyt pieleen
//        }    
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
            System.out.println(s);
            if (s.equals("a") || s.equals("t") || s.equals("c") || s.equals("g") || s.equals("\n")) {
            } else {
                return 0;
            }
        } 
//        String onko = searchOne("sekvenssit.txt", nimi, 2); 
//        if (onko.equals(nimi)) {
//            return -1; //laji on jo lisätty tietokantaan
//        } else {
//            Laji uusiLaji = new Laji(sekvenssi, nimi);
//            this.lajit.add(uusiLaji);
//            addToDb("sekvenssit.txt", sekvenssi, nimi);
//            return 1; //lisätään tietokantaan
//        }
        if (!this.lajit.isEmpty()) {            
            for (Laji laji : this.lajit) {
                if (laji.getLaji().equals(nimi)) { //laji on jo listassa
                    return -1;
                }                
            }
            Laji l = new Laji(sekvenssi, nimi);
            this.lajit.add(l);
            addToDb("sekvenssit.txt", sekvenssi, nimi);
            return 1;           
        } else {
            Laji l = new Laji(sekvenssi, nimi);
            this.lajit.add(l);
            addToDb("sekvenssit.txt", sekvenssi, nimi);
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
    public void fromDbToSeq() {
        try {
            ArrayList<String>species = new ArrayList<>();
            ArrayList<String>seq = new ArrayList<>();
            FileReader reader = new FileReader("sekvenssit.txt");
            BufferedReader bufferedReader = new BufferedReader(reader); 
            String line;
            String adding = "";
            while ((line = bufferedReader.readLine()) != null) {
                for (String s: line.split("")) {
                    if (s.equals(":")) {
                        if (adding.contains(" ")) {
                            species.add(adding); //laji ja sekvenssi ovat eri taulukoissa mutta samassa indeksissä
                            adding = "";
                        } else {
                            seq.add(adding);
                            adding = "";
                        }
                    } else {
                        adding = adding + s;
                    }
                }
            }
            int i = 0;
            while (i < seq.size()) {
                Laji l = new Laji(seq.get(i), species.get(i));
                this.lajit.add(l);
                i++;
            }       
        } catch (IOException e) {  
        }
    }
    public void fromDbToUser() {
        try {
            ArrayList<String>haettavat = new ArrayList<>();
            ArrayList<String>parilliset = new ArrayList<>();
            ArrayList<String>parittomat = new ArrayList<>();
            FileReader reader = new FileReader("kayttajat.txt");
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
            i = 0;
            while (i < parilliset.size()) {
                Kayttaja k = new Kayttaja(parilliset.get(i), parittomat.get(i));
                this.kayttajat.add(k);
                i++;
            }
        } catch (IOException e) {
            
        }
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
            bufferedWriter.write(dataKayttajatunnus + ":" + lajiSalasana + ":");
            bufferedWriter.newLine();
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
//    public String searchTwo(String tiedosto, String tunnusSekvenssi, String salasanaLaji, int tVaiS) {
//        try {
//            ArrayList<String>haettavat = new ArrayList<>();
//            ArrayList<String>parilliset = new ArrayList<>();
//            ArrayList<String>parittomat = new ArrayList<>();
//            FileReader reader = new FileReader(tiedosto);
//            BufferedReader bufferedReader = new BufferedReader(reader); 
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                haettavat.addAll(Arrays.asList(line.split(":")));
//            }
//            int i = 0;
//            while (i < haettavat.size()) {
//                if (i % 2 != 0) {
//                    parittomat.add(haettavat.get(i));
//                } else {
//                    parilliset.add(haettavat.get(i));
//                }
//                i++;
//            }
//            if (tVaiS == 1) {
//                if (searchOne(tiedosto, tunnusSekvenssi, tVaiS).equals(tunnusSekvenssi)) {
//                    int pwIndex = parilliset.indexOf(tunnusSekvenssi);
//                    if (parittomat.get(pwIndex).equals(salasanaLaji)) {
//                        return "Kirjautuminen onnistui";
//                    }
//                    return "Salasana väärin";
//                }
//                return "Käyttäjätunnusta ei löytynyt";
//            }
//            else if (tVaiS == 2) {
//                if (searchOne(tiedosto, salasanaLaji, tVaiS).equals(salasanaLaji)) {
//                    int pwIndex = parittomat.indexOf(salasanaLaji);
//                    if (tunnusSekvenssi.contains(parilliset.get(pwIndex))) {
//                        return "jotain";
//                    } 
//                }
//            }
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
//            
//            reader.close();
//          
//        } catch (IOException e) {
//
//        }
//        return "Haku ei toiminut";
//    }
//    public String searchOne(String tiedosto, String haettava, int tunnusVaiSekvenssi) {
//        try {
//            ArrayList<String>haettavat = new ArrayList<>();
//            ArrayList<String>parilliset = new ArrayList<>();
//            List<String>parittomat = new ArrayList<>();
//            FileReader reader = new FileReader(tiedosto);
//            BufferedReader bufferedReader = new BufferedReader(reader); 
//            String line;
//            while ((line = bufferedReader.readLine()) != null) { 
//                haettavat.addAll(Arrays.asList(line.split(":")));
//            }
//            int i = 0;
//            while (i < haettavat.size()) {
//                if (i % 2 != 0) {
//                    parittomat.add(haettavat.get(i));
//                } else {
//                    parilliset.add(haettavat.get(i));
//                }
//                i++;
//            }
//            if (tunnusVaiSekvenssi == 1) { //haetaan käyttäjätunnusta
//                for (String s : parilliset) {
//                    if (s.equals(haettava)) {
//                        reader.close();
//                        return haettava; //tunnus on olemassa
//                    }
//                }
//                reader.close();
//                return ""; //tunnusta ei ole
//            } else if (tunnusVaiSekvenssi == 2) { //haetaan lajia
//                for (String s : parittomat) {
//                    if (s.equals(haettava)) {
//                        reader.close();
//                        return haettava; //laji löytyy
//                    }
//                }
//                reader.close();
//                return "";
//            }
//            
//        }catch (IOException e) {
//            
//        }
//        return "";
//    }
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
