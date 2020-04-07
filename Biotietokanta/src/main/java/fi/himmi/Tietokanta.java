package fi.himmi;


import fi.himmi.LajiDao;
import fi.himmi.Laji;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;



public class Tietokanta implements LajiDao<Laji, Integer> {
    
//    public void luoTauluSekvenssit() throws SQLException {
//        Connection conn = DriverManager.getConnection("jdbc:sqlite:biotietokanta.db");
//        System.out.println("Saatiin yhteys tietokantaan.");
//        
//        try {
//            conn.prepareStatement("CREATE TABLE Sekvenssit"
//                    + "(id integer primary key AUTOINCREMENT, "
//                    + "data text"
//                    + "laji text)").execute();
//            System.out.println("Tietokanta saatiin luotua");
//        } catch (SQLException t) {  
//            
//        }
//        conn.close();
//    }
    public void poistaSekvenssit() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:biotietokanta.cb");
        conn.prepareStatement("DROP TABLE Sekvenssit").execute();
        System.out.println("Sekvenssit poistettiin.");
    }
    
    @Override
    public void create(Laji laji) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:biotietokanta.db");
        System.out.println("Createssa yhteys");
        
        connection.prepareStatement("CREATE TABLE Sekvenssit"
                    + "(id integer primary key, "
                    + "data text"
                    + "laji text)").execute();
        
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Sekvenssit (data, laji) values (?,?)",Statement.RETURN_GENERATED_KEYS);
        System.out.println("Saatiin lisättyä tiedot");
        stmt.setString(1, laji.getData());
        stmt.setString(2, laji.getLaji());
        
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    @Override
    public Laji read(Integer key) throws SQLException {

        return null;
    }

    @Override
    public Laji update(Laji object) throws SQLException {

        return null;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }

    @Override
    public List<Laji> list() throws SQLException {
        // ei toteutettu
        return null;
    }
}