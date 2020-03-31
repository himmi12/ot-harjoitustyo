/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    public void salasanaOnVahintaanKuusiMerkkia() {
        assertFalse(biotietokanta.luoTunnus("testi1", "viisi"));
        assertTrue(biotietokanta.luoTunnus("testi2", "seitseman"));
    }
    @Test
    public void kayttajatunnusOnUniikki() {
        biotietokanta.luoTunnus("testi3", "salasana");
        assertFalse(biotietokanta.luoTunnus("testi3", "salasana"));
        assertTrue(biotietokanta.luoTunnus("testi4", "salasana"));
    }
    @Test
    public void salasanaOnOikein() {
        biotietokanta.luoTunnus("testi5", "onkoOikein");
        assertFalse(biotietokanta.kirjauduSisaan("testi5", "EiOleOikein"));
        assertTrue(biotietokanta.kirjauduSisaan("testi5", "onkoOikein"));
    }
    @Test
    public void kayttajatunnusOnOlemassa() {
        biotietokanta.luoTunnus("testi6", "salasana");
        assertTrue(biotietokanta.kirjauduSisaan("testi6", "salasana"));
        assertFalse(biotietokanta.kirjauduSisaan("vaaraTunnus", "vaaraSalasana"));
    }   
}
