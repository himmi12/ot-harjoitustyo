
import java.util.Scanner;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        String tietokannanPolku = "jdbc:sqlite:com.mycompany.biotietokanta"; 
        if (args.length > 0) {
            tietokannanPolku = args[0];
        }
 
        Tietokanta tietokanta = new Tietokanta(tietokannanPolku);
        Scanner lukija = new Scanner(System.in);
 
        new Biotietokanta(lukija, tietokanta).kaynnista();
    }
}
