package fi.himmi.logics;

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
    public String getTunnus() {
        return this.tunnus;
    }
    public String getSalasana() {
        return this.salasana;
    }
}
