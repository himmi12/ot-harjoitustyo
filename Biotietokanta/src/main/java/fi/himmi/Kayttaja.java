package fi.himmi;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author himmi
 */
public class Kayttaja {
    
    private String tunnus;
    private String salasana;
    
    public Kayttaja(String tunnus, String salasana) {
        this.tunnus = tunnus;
        this.salasana = salasana;
    }
    public void setTunnus(String tunnus) {
        this.tunnus = tunnus;
    }
    public String getTunnus() {
        return this.tunnus;
    }
    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }
    public String getSalasana() {
        return this.salasana;
    }
}
