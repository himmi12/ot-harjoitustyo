package fi.himmi.logics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.InputStreamReader;


public class Biotietokanta {
    
    private List<Kayttaja> kayttajat;
    private List<Laji> lajit;
    
    public Biotietokanta() {
        
        this.kayttajat = new ArrayList<>();
        this.lajit = new ArrayList<>();
    }
    public int luoTunnus(String tunnus, String salasana) {
        if (salasana.length() < 6) {
            return -1; // salasana liian lyhyt            
        }
        String onko = searchFromDb("kayttajat.txt", tunnus, salasana, 1); // numero 1 kertoo että haetaan käyttäjätunnusta
        if (onko.equals("t")) {
            return 0; //tunnus on varattu
        } else {
            Kayttaja uusiKayttaja = new Kayttaja(tunnus, salasana);
            this.kayttajat.add(uusiKayttaja);
            addToDb("kayttajat.txt", tunnus, salasana);
            return 1; //lisätään tietokantaan
        }

    }
    
    public int kirjauduSisaan(String tunnus, String salasana) {
        
        String onnistuuko = searchFromDb("kayttajat.txt", tunnus, salasana, 2); 
        
        if (onnistuuko.equals("v")) {
            return 0; //käyttäjätunnus löytyi mutta salasana on väärin
        }
        else if (onnistuuko.equals("f")) {
            return -1; //käyttäjätunnusta ei ole luotu
        }
        else if (onnistuuko.equals("onnistuu")) {
            return 1; //kirjautuminen onnistuu
        }
        else {
            return -2; //jotain on mennyt pieleen
        }    
//        if (!this.kayttajat.isEmpty()) {
//            for (Kayttaja kayttajatunnus : this.kayttajat) {
//                if (kayttajatunnus.getTunnus().equals(tunnus)) {
//                
//                    if (kayttajatunnus.getSalasana().equals(salasana)) {
//                        return 1; //salasana on oikein
//                    } else {
//                        return 0; //salasana on väärin
//                    }
//                }
//            }
//        }            
//        return -1; //käyttäjätunnusta ei löydy
    }

    public int add(String sekvenssi, String nimi) {
        sekvenssi = sekvenssi.toLowerCase();        
        for (String s: sekvenssi.split("")) {            
            if (s.equals("a") || s.equals("t") || s.equals("c") || s.equals("g") || s.equals("\n")) {
            }
            else {
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
    public void addToDb(String tiedosto, String dataKayttajatunnus, String lajiSalasana) {
        try {
            FileWriter writer = new FileWriter(tiedosto, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer); 
            bufferedWriter.newLine();
            bufferedWriter.write(dataKayttajatunnus+":"+lajiSalasana); 
            bufferedWriter.close();
        } catch (IOException e) {

        }
    }
    public String searchFromDb(String tiedosto, String tunnus, String salasana, int ekaVaiToka) {
        try {
            FileReader reader = new FileReader(tiedosto);
            BufferedReader bufferedReader = new BufferedReader(reader); 
            String line;
            Boolean tunnusOikein=false;
            while ((line = bufferedReader.readLine()) != null) {
                for (String s: line.split(":")) {
                    if (s.equals(tunnus)) {
                        if (ekaVaiToka == 1) {
                            reader.close();
                            return "t"; // käyttäjä löytyi tietokannasta
                        } else if (ekaVaiToka == 2) {
                            tunnusOikein=true;
                        }                        
                    } else if (s.equals(salasana) && tunnusOikein==true && ekaVaiToka==2) {
                            reader.close();
                            return "onnistuu";
                    } else if (!s.equals(salasana) && tunnusOikein==true && ekaVaiToka==2) {
                        return "v"; //käyttäjä löytyi mutta salasana väärin
                    }                    
                }
            }
            reader.close();
            return "f"; //käyttäjätunnusta ei löytnyt
 
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
