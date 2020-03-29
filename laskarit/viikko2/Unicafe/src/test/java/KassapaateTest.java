/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.unicafe.Kassapaate;
import com.mycompany.unicafe.Maksukortti;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class KassapaateTest {
    
    Kassapaate kassapaate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
    }
    
    @Test
    public void kassapaateOnOlemassa() {
        assertTrue(kassapaate!=null);
    }
    @Test
    public void oikeatAlkuparametrit() {
        assertTrue(kassapaate.kassassaRahaa()==100000);
        assertTrue(kassapaate.edullisiaLounaitaMyyty()==0);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty()==0);
    }
    @Test
    public void edullisissaKateismaksuRiittava() {
        assertTrue(kassapaate.syoEdullisesti(500)==260);
        assertTrue(kassapaate.kassassaRahaa()==100240);
        assertTrue(kassapaate.edullisiaLounaitaMyyty()==1);
    }
    @Test
    public void edullisissaKateismaksuEiRiita() {
        assertTrue(kassapaate.syoEdullisesti(100)==100);
        assertTrue(kassapaate.kassassaRahaa()==100000);
        assertTrue(kassapaate.edullisiaLounaitaMyyty()==0);
    }
    @Test
    public void maukkaissaKateismaksuRiittava() {
        assertTrue(kassapaate.syoMaukkaasti(500)==100);
        assertTrue(kassapaate.kassassaRahaa()==100400);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty()==1);
    }
    @Test
    public void maukkaissaKateismaksuEiRiita() {
        assertTrue(kassapaate.syoMaukkaasti(100)==100);
        assertTrue(kassapaate.kassassaRahaa()==100000);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty()==0);
    }
    @Test
    public void edullisissaKorttiostoRiittava() {
        kortti = new Maksukortti(1000);
        assertTrue(kassapaate.syoEdullisesti(kortti));
        assertTrue(kassapaate.kassassaRahaa()==100000);
        assertTrue(kassapaate.edullisiaLounaitaMyyty()==1);
    }
    @Test
    public void edullisissaKorttiostoEiRiita() {
        kortti = new Maksukortti(1);
        assertFalse(kassapaate.syoEdullisesti(kortti));
        assertTrue(kassapaate.kassassaRahaa()==100000);
        assertTrue(kassapaate.edullisiaLounaitaMyyty()==0);
    }
    @Test
    public void maukkaissaKorttiostoRiittava() {
        kortti = new Maksukortti(1000);
        assertTrue(kassapaate.syoMaukkaasti(kortti));
        assertTrue(kassapaate.kassassaRahaa()==100000);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty()==1);
    }
    @Test
    public void maukkaissaKorttiostoEiRiita() {
        kortti = new Maksukortti(1);
        assertFalse(kassapaate.syoMaukkaasti(kortti));
        assertTrue(kassapaate.kassassaRahaa()==100000);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty()==0);
    }
    @Test
    public void kortinLataaminenToimii() {
        kortti = new Maksukortti(200);
        kassapaate.lataaRahaaKortille(kortti, 800);
        assertTrue(kassapaate.kassassaRahaa()==100800);
        assertTrue(kortti.saldo()==1000);        
    }
    @Test
    public void kortinLataaminenToimiiVirheellisissakinTapauksissa() {
        kortti = new Maksukortti(200);
        kassapaate.lataaRahaaKortille(kortti, -100);
        assertTrue(kassapaate.kassassaRahaa()==100000);
        assertTrue(kortti.saldo()==200);
    }
}
