
import java.util.Scanner;
import java.sql.SQLException;
import java.sql.*;


public class Main {
    public static void main(String[] args) throws SQLException {
        
        Tietokanta tietokanta = new Tietokanta();
        Scanner lukija = new Scanner(System.in);
        
//        tietokanta.poistaSekvenssit();
 
        Biotietokanta biotietokanta=new Biotietokanta(lukija, tietokanta);
        biotietokanta.luoTunnus("tutkija123", "salasana");        
        biotietokanta.luoTunnus("biologi", "lehtokurppa2020");
        biotietokanta.luoTunnus("tutkija123", "Provinssi2005");
        
        biotietokanta.kirjauduSisaan("tutkija123", "salasana");
        biotietokanta.kirjauduSisaan("biologi", "lehtokurppa");
    }
}
