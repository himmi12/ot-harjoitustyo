
import java.util.Scanner;
import java.sql.SQLException;


public class Biotietokanta {
    
    private Scanner lukija;
    private Tietokanta tietokanta;
    
    public Biotietokanta(Scanner lukija, Tietokanta tietokanta) {
        this.lukija=lukija;
        this.tietokanta=tietokanta;
    }
    
    public void kaynnista() throws SQLException {
        while (true) {
            
            String komento = this.lukija.nextLine();
            if (komento.equals("x")) {
                break;
            }

            if (komento.equals("1")) {
                lisaa();
//            } else if (komento.equals("2")) {
//                haeLajinNimea();
            }
        }    
    }
    public void lisaa() throws SQLException {
        System.out.println("Syötä ensin sekvenssi ja sitten nimi");
        String sekvenssi = this.lukija.nextLine();
        String nimi = this.lukija.nextLine();
        
        boolean onkoTietokannassa=this.tietokanta.haeNimella(nimi);
        System.out.println(onkoTietokannassa);
        
        if (onkoTietokannassa==false) {
            Laji uusiLaji = new Laji(sekvenssi, nimi);
            this.tietokanta.lisaa(uusiLaji);
            System.out.println("Laji lisattiin");
        }
    }
//    public void haeLajinNimea() throws SQLException {
//        
//    }
}
