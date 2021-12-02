package kata5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author saul
 */
public class Kata5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        connect();
        SelectApp app = new SelectApp();
        app.selectAll();
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
