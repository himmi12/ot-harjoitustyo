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
    private String usersFile;
    private String speciesFile;
    private List<Kayttaja> kayttajat;
    private List<Laji> lajit;
    
    public Biotietokanta(String users, String species) {
        this.usersFile = users;
        this.speciesFile = species;
        this.kayttajat = new ArrayList<>();
        this.lajit = new ArrayList<>();
    }
    
    /**
     * Luodaan käyttäjätunnus, jos luotava tunnus täyttää tunnuksen luomisen kriteerit.
     * @param tunnus
     * @param salasana
     * @return -2, -1, 0 tai 1 
     */
    
    public int luoTunnus(String tunnus, String salasana) {
        if (salasana.length() < 6) {
            return -1;            
        }
        if (tunnus.isEmpty()) {
            return -2;
        }
        if (this.kayttajat.isEmpty()) {            
            Kayttaja k = new Kayttaja(tunnus, salasana);
            this.kayttajat.add(k);
            addToDb(this.usersFile, tunnus, salasana);
            return 1;           
        } else {
            for (Kayttaja k: kayttajat) {
                if (k.getTunnus().equals(tunnus)) {
                    return 0;
                }                
            }
            Kayttaja k = new Kayttaja(tunnus, salasana);
            this.kayttajat.add(k);
            addToDb(this.usersFile, tunnus, salasana);
            return 1; 
        }
    }
    /**
     * Kirajudutaan sisään tarkistaen, onko tunnus tietokannassa ja vastaako syötteen salasana tietokannan salasanaa.
     * @param tunnus
     * @param salasana
     * @return -1,0 tai 1 Riippuen siitä, mikä kirjautumisessa on epäonnistunut vai onko kirjautuminen 
     * onnistunut
     */
    
    public int kirjauduSisaan(String tunnus, String salasana) {
        for (Kayttaja k : kayttajat) {
            if (k.getTunnus().equals(tunnus)) {
                if (k.getSalasana().equals(salasana)) {
                    return 1;
                } else {
                    return 0;
                }                
            }
        }
        return -1;  
    }
    /**
     * Lisätään sekvenssi ja lajin nimi tietokantaan sekä työmuistina käytettävään listaan, jos kriteerit täyttyvät.
     * @param sekvenssi
     * @param nimi
     * @return -2,-1,0 tai 1 riippuen siitä, mikä on mennyt pieleen vai onko lisääminen onnistunut
     */

    public int add(String sekvenssi, String nimi) {
        sekvenssi = sekvenssi.toLowerCase();        
        for (String s: sekvenssi.split("")) {  
            if (!s.equals("a") && !s.equals("t") && !s.equals("c") && !s.equals("g") && !s.equals("\n")) {
                return 0;
            }
        }
        if (nimi.contains(" ")) {
            if (this.lajit.isEmpty()) {
                Laji l = new Laji(sekvenssi, nimi);
                this.lajit.add(l);
                addToDb(this.speciesFile, sekvenssi, nimi);
                return 1;
            } else {
                for (Laji laji : this.lajit) {
                    if (laji.getLaji().equals(nimi)) {
                        return -1;
                    }
                }
                Laji l = new Laji(sekvenssi, nimi);
                this.lajit.add(l);
                addToDb(this.speciesFile, sekvenssi, nimi);
                return 1;
            }
        }       
        return -2;
    }
    /**
     * Haetaan lajin nimi työmuistista sen perusteella sisältääkö listan sekvenssi haettavan sekvenssin.
     * @param sekvenssi
     * @return lista, sopivista lajien nimistä
     */
    public List search(String sekvenssi) {
        List<String> matches = new ArrayList<>();
        if (sekvenssi.isEmpty()) {
            return matches;
        }
        sekvenssi = sekvenssi.toLowerCase(); 
        for (Laji laji: this.lajit) {
            if (laji.getData().contains(sekvenssi)) {
                matches.add(laji.getLaji());
            }
        }
        return matches;        
    }
    /** 
     * Luetaan sekvenssit.txt -tiedostosta työmuistiin eli this.lajit-listaan lajien nimet ja DNA-sekvenssit. 
     * @return true, jos kaikki menee normaalisti, muuten false
     */
    public boolean fromDbToSeq() {
        try {
            ArrayList<String> species = new ArrayList<>();
            ArrayList<String> seq = new ArrayList<>();
            FileReader reader = new FileReader(this.speciesFile);
            BufferedReader bufferedReader = new BufferedReader(reader); 
            String line;
            String adding = "";
            while ((line = bufferedReader.readLine()) != null) {
                for (String s: line.split("")) {
                    if (s.equals(":")) {
                        if (adding.contains(" ")) {
                            species.add(adding);
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
            if (seq.isEmpty()) {
                this.lajit.isEmpty();
                return true;
            } else {
                while (i < seq.size()) {
                    Laji l = new Laji(seq.get(i), species.get(i));
                    this.lajit.add(l);
                    i++;
                }
                return true;
            }

        } catch (IOException e) {
        }
        return false;
    }
    /**
     * Luetaan kayttajat.txt -tiedostosta työmuistiin eli this.kayttajat-listaan käyttäjätunnukset
     * ja niihin liittyvät salasanat.
     * @return true, jos kaikki menee normaalisti, tai false
     */
    public boolean fromDbToUser() {
        try {
            ArrayList<String> haettavat = new ArrayList<>();
            ArrayList<String> parilliset = new ArrayList<>();
            ArrayList<String> parittomat = new ArrayList<>();
            FileReader reader = new FileReader(this.usersFile);
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
            if (parilliset.isEmpty()) {
                this.kayttajat.isEmpty();
                return true;
            } else {
                while (i < parilliset.size()) {
                    Kayttaja k = new Kayttaja(parilliset.get(i), parittomat.get(i));
                    this.kayttajat.add(k);
                    i++;
                }
                return true;
            }
           
        } catch (IOException e) {
            
        }
        return false;
    }
    /**
     * Lisätään valittuun tekstitiedostoon joko käyttäjätunnus ja salasana tai DNA-sekvenssi ja laji.
     * Tiedostoksi voi valita joko kayttajat tai sekvenssit -tiedoston
     * Kaksoispisteet erottavat tunnuksen ja salasanan sekä sekvenssidatan ja lajin nimen toisistaan.
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
}
