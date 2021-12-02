package kata5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author saul
 */
public class Kata5 {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        connect();
        createTable(DriverManager.getConnection("jdbc:sqlite:KATA5.db"));
        //SelectApp app = new SelectApp();
        //app.selectAll();
    }
    
    private static void createTable(Connection conection)throws SQLException{
        
        String sentence = """
            CREATE TABLE IF NOT EXISTS EMAIL(
            Id integer primary key autoincrement,
            Mail text NOT NULL);""";
        
                Statement stmt = conection.createStatement();
        stmt.execute(sentence);
    }    
    
    private static void connect() {
        Connection conn = null;
        try {
        // parámetros de la BD
        String url = "jdbc:sqlite:KATA5.db";
        // creamos una conexión a la BD
        conn = DriverManager.getConnection(url);
        System.out.println("Connexión a SQLite establecida");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
