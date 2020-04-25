package fi.himmi.dao;

//
//import fi.himmi.logics.Laji;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.List;

//Tässä kaikki on työn alla ja ohjelma ei vielä tallenna tietoa tietokantaan.

//public class LajiDaoClass implements LajiDao<Laji, Integer> {
//    
//    private String polku;
//    
//    public LajiDaoClass(String polku) {
//        this.polku=polku;
//    }
//    private Connection varmistaDb() throws SQLException {
//        Connection conn = DriverManager.getConnection("jdbc:h2:./sekvenssit", "sa", "");
//        
//        try {            
//            conn.prepareStatement("DROP TABLE Sekvenssit IF EXISTS").execute();
//            conn.prepareStatement("CREATE TABLE Sekvenssit"
//                    + "(id int auto_increment primary key, "
//                    + "data varchar(1000)"
//                    + "laji varchar(100))").execute();
//            System.out.println("Sekvenssit luotiin");
//        } catch (SQLException e) {  
//            System.out.println("Ei onnistunut");
//        }
//        return conn;
//    }
    
//    public void create(Laji laji) throws SQLException {
//        try (Connection connection = varmistaDb()) {
//            System.out.println("Createssa yhteys");
//        
//        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Sekvenssit (data, laji) values (?,?)", Statement.RETURN_GENERATED_KEYS);
//        System.out.println("Saatiin lisättyä tiedot");
//        stmt.setString(1, laji.getData());
//        stmt.setString(2, laji.getLaji());
//        
//        stmt.executeUpdate();
//        stmt.close();
//        connection.close();
//        }
//    }

//    @Override
//    public Laji read(Integer key) throws SQLException {
//
//        return null;
//    }
//
//    @Override
//    public Laji update(Laji object) throws SQLException {
//
//        return null;
//    }
//
//    @Override
//    public void delete(Integer key) throws SQLException {
//        // ei toteutettu
//    }
//
//    @Override
//    public List<Laji> list() throws SQLException {
//        // ei toteutettu
//        return null;
//    }
//}

//    public static void luoSekvenssit() {
//        
//        try {
//            Connection conn = DriverManager.getConnection("jdbc:h2:./sekvenssit", "sa", "");
//            conn.prepareStatement("DROP TABLE Sekvenssit IF EXISTS").execute();
//            conn.prepareStatement("CREATE TABLE Sekvenssit"
//                    + "(id int auto_increment primary key, "
//                    + "data varchar(1000)"
//                    + "laji varchar(100))").execute();
//            System.out.println("Sekvenssit luotiin");
//        } catch (SQLException e) {  
//            System.out.println("Ei onnistunut");
//        }
//    }
//    public static void luoKayttajat() {
//        
//        try {
//            Connection conn = DriverManager.getConnection("jdbc:sqlite:kayttajat");
//            conn.prepareStatement("DROP TABLE Kayttajat IF EXISTS;").execute();
//            conn.prepareStatement("CREATE TABLE Kayttajat"
//            +"(id integer AUTO_INCREMENT primary key,"
//                    + "tunnus text"
//                    + "salasana text);"). execute();
//            System.out.println("Kayttajat luotiin");
//        } catch (SQLException e) {
//            
//        }
//    }