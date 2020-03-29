package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoVaheneeOikein() {
        kortti.otaRahaa(1);
        assertEquals("saldo: 0.9", kortti.toString());
    }
    @Test
    public void saldoEiMuutuJosRahaaEiTarpeeksi() {
        kortti.otaRahaa(500);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    @Test
    public void metodiPalauttaaTrue() {
        assertTrue(kortti.otaRahaa(1));
    }
    @Test
    public void metodiPalauttaaFalse() {
        assertFalse(kortti.otaRahaa(200));
    }
}
