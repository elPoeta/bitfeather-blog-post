
package elpoeta.bitfeather.bd.conexion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author elpoeta
 */
public class Conexion {
      private static Conexion INSTANCE = null;
     private static String LABASE = "jdbc:hsqldb:file:~/BBDD/bitfeatherdb/bitfeather_db";
     //private static String LABASE = "jdbc:hsqldb:file:resources/bitfeather/bitfeather_db";
     private static String LABASEUSUARIO = "SA";
     private static String LABASECLAVE = "";
    
	public static Conexion getInstance() throws ClassNotFoundException, IOException, SQLException {
         if (INSTANCE == null) {
             INSTANCE = new Conexion();
         }
         return INSTANCE;
     }
     private Conexion() throws ClassNotFoundException,
             IOException, SQLException {
     }

     public Connection getConnection() throws ClassNotFoundException,
             IOException, SQLException {
         Class.forName("org.hsqldb.jdbc.JDBCDriver");
         return DriverManager.getConnection(LABASE, LABASEUSUARIO, LABASECLAVE);
     }
}
