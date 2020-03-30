import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Tietokanta {
    
    private String tietokannanPolku;
    
    public Tietokanta(String tietokannanPolku) {
        this.tietokannanPolku=tietokannanPolku;
    }
    
    public Connection luoYhteysJaVarmistaTietokanta() throws SQLException {
        Connection conn = DriverManager.getConnection(this.tietokannanPolku);
        
        try {
            conn.prepareStatement("CREATE TABLE Sekvenssit "
                    + "(id integer primary key AUTOINCREMENT, "
                    + "data text"
                    + "laji text)").execute();
        } catch (SQLException t) {  
            
        }
        return conn;
    }
    
    public void lisaa(Laji laji) throws SQLException {
        try (Connection yhteys = luoYhteysJaVarmistaTietokanta()) {
            PreparedStatement stmt = yhteys.prepareStatement("INSERT INTO Sekvenssit (data, laji) VALUES (?,?)");
            stmt.setString(1, laji.getData());
            stmt.setString(2, laji.getNimi());
            stmt.executeUpdate();
        }
    }
    public boolean haeNimella(String laji) throws SQLException {
        try (Connection yhteys =luoYhteysJaVarmistaTietokanta()) {
            PreparedStatement p=yhteys.prepareStatement("SELECT laji FROM Sekvenssit WHERE laji=?");
            p.setString(1, laji);
            
            ResultSet r = p.executeQuery();
            if (r.next()) {
                return true;
            } else {
                return false;
            }
        }
    }
//    public String haeLajinNimea(String sekvenssi) throws SQLException {
//        try (Connection yhteys=luoYhteysJaVarmistaTietokanta()) {
//            PreparedStatement p=yhteys.prepareStatement("SELECT laji FROM Sekvenssit WHERE data=?");
//            p.setString(1, sekvenssi);
//            
//            ResultSet r = p.executeQuery();
//            if (r.next()) {
//                return r.getString(1);
//            } else {
//                return "Syötetty DNA-sekvenssi ei vastannut mitään tietokannassa olevaa lajia";
//            }
//        }
//    }
    
}
