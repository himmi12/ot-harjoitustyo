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
public class Laji {
    private String sekvenssi;
    private String lajinNimi;
    
    public Laji(String sekvenssi, String nimi) {
        this.sekvenssi=sekvenssi;
        this.lajinNimi=nimi;
    }
    public String getLaji() {
        return this.lajinNimi;
    }
    public String getData() {
        return this.sekvenssi;
    }
    public void setLaji(String nimi) {
        this.lajinNimi=nimi;
    }
    public void setData(String sekvenssi) {
        this.sekvenssi=sekvenssi;
    }
}
