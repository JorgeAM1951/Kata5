package kata5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws SQLException, 
            FileNotFoundException, IOException {
        // TODO code application logic here
        connect();
        createTable(DriverManager.getConnection("jdbc:sqlite:KATA5.db"));
        //SelectApp app = new SelectApp();
        //app.selectAll();
    }
    
    private static void createTable(Connection conection)throws 
            SQLException, FileNotFoundException, IOException{
        
        String mail;
        
        String sentence = """
            CREATE TABLE IF NOT EXISTS EMAIL(
            Id integer primary key autoincrement,
            Mail text NOT NULL);""";
        
        Statement statement = conection.createStatement();
        statement.execute(sentence);
        
        try(BufferedReader buferedRead = new BufferedReader(new FileReader("email.txt")))
        {
            
            
            String sentenceInsert = "INSERT INTO EMAIL(Mail) VALUES (?)";
            
            PreparedStatement pStatement = conection.prepareStatement(sentenceInsert);
            
            while((mail = buferedRead.readLine()) != null){
                pStatement.setString(1,mail);
                pStatement.executeUpdate();
            }
            
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        
        System.out.println("Actualización de tabla realizada");
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
