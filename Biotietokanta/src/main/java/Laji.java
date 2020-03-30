/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author himmi
 */
public class Laji {
    private String sekvenssi;
    private String nimi;
    
    public Laji(String sekvenssi, String nimi) {
        this.sekvenssi=sekvenssi;
        this.nimi=nimi;
    }
    public String getNimi() {
        return this.nimi;
    }
    public String getData() {
        return this.sekvenssi;
    }
    public void setNimi(String nimi) {
        this.nimi=nimi;
    }
    public void setData(String sekvenssi) {
        this.sekvenssi=sekvenssi;
    }
}
