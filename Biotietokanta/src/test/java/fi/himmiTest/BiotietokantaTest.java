package fi.himmiTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fi.himmi.Biotietokanta;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author himmi
 */
public class BiotietokantaTest {
    
    Biotietokanta biotietokanta;
    
    @Before
    public void setUp() {
        biotietokanta = new Biotietokanta();
    }
    @Test
    public void tietokantaOnOlemassa() {
        assertTrue(biotietokanta!=null);
    }
    
    @Test
    public void salasanaOnVahintaanKuusiMerkkia() {
        assertTrue(biotietokanta.luoTunnus("testi1", "viisi")==-1);
        assertTrue(biotietokanta.luoTunnus("testi2", "seitseman")==1);
    }
    @Test
    public void kayttajatunnusOnUniikki() {
        biotietokanta.luoTunnus("testi3", "salasana");
        assertTrue(biotietokanta.luoTunnus("testi3", "salasana")==0);
        assertTrue(biotietokanta.luoTunnus("testi4", "salasana")==1);
    }
    @Test
    public void salasanaOnOikein() {
        biotietokanta.luoTunnus("testi5", "onkoOikein");
        assertTrue(biotietokanta.kirjauduSisaan("testi5", "EiOleOikein")==0);
        assertTrue(biotietokanta.kirjauduSisaan("testi5", "onkoOikein")==1);
    }
    @Test
    public void kayttajatunnusOnOlemassa() {
        biotietokanta.luoTunnus("testi6", "salasana");
        assertTrue(biotietokanta.kirjauduSisaan("testi6", "salasana")==1);
        assertTrue(biotietokanta.kirjauduSisaan("vaaraTunnus", "vaaraSalasana")==-1);
    }   
}
