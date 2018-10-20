
package elpoeta.bitfeather.dao;

import elpoeta.bitfeather.bd.conexion.Conexion;
import elpoeta.bitfeather.modelo.Autor;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author elpoeta
 */
public class AutorDao {
      private static AutorDao INSTANCE = null;
    private final static String SQL_AUTORES_SELECT = "SELECT * FROM autor;";
    private final static String SQL_AUTOR_SELECT = "SELECT * FROM autor WHERE id = ?;";
    private final static String SQL_AUTOR_FIND_BY_EMAIL_AND_NOMBRE = "SELECT * FROM autor WHERE email = ? OR nombre = ?;";
    private final static String SQL_AUTOR_FIND_BY_EMAIL = "SELECT * FROM autor WHERE email = ?;";
    private final static String SQL_AUTOR_INSERT = "INSERT INTO autor (nombre, email, password, avatar)values(?,?,?,?);";
    
    private AutorDao() throws ClassNotFoundException,
    IOException, SQLException {
}
    public static AutorDao getInstance() throws ClassNotFoundException,
	IOException, SQLException {
		if (INSTANCE == null) {
			INSTANCE = new AutorDao();
		}
		return INSTANCE;
	}
    
    public Autor buscarPorId(Integer id) throws ClassNotFoundException, IOException, SQLException {
         Autor autor = null;
		Connection conexion = null;
		PreparedStatement ptsmt = null;
		ResultSet rs = null;
		try {
		conexion = Conexion.getInstance().getConnection();
		ptsmt = conexion.prepareStatement(SQL_AUTOR_SELECT);
		ptsmt.setInt(1, id);
		rs = ptsmt.executeQuery();
		
		if(rs.next()) {
		    try {
		        autor = new Autor();
		        autor.setId(rs.getInt("id"));
		        autor.setNombre(rs.getString("nombre"));
                        autor.setEmail(rs.getString("email"));
                        autor.setPassword(rs.getString("password"));
                        autor.setIs_activo(rs.getBoolean("is_activo"));
                        autor.setAvatar(rs.getString("avatar"));
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		}
		} finally {
		try {
		    rs.close();
		} finally {
		    try {
		        ptsmt.close();
		    } finally {
		        conexion.close();
		    }
		}
		}
		return autor;

    }

   
    public List<Autor> buscarTodos() throws ClassNotFoundException, IOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    public void insertar(Autor autor) throws ClassNotFoundException, IOException, SQLException {
         Connection c = null;
		   PreparedStatement ptsmt = null;
		   try {
		       c = Conexion.getInstance().getConnection();
		       ptsmt = c.prepareStatement(SQL_AUTOR_INSERT);
                       ptsmt.setString(1, autor.getNombre());
		       ptsmt.setString(2,  autor.getEmail());
                       String hashed = BCrypt.hashpw(autor.getPassword(), BCrypt.gensalt());
                       System.out.println("cryt > "+hashed);
		       ptsmt.setString(3, hashed);
                       ptsmt.setString(4, autor.getAvatar());
		       ptsmt.execute();
		   } finally {
		       try {
		           ptsmt.close();
		       } finally {
		           c.close();
		       }
		   }
    }

    
     public Autor buscarPorEmailAndNombre(String email, String nombre) throws ClassNotFoundException, IOException, SQLException {
         Autor autor = null;
		Connection conexion = null;
		PreparedStatement ptsmt = null;
		ResultSet rs = null;
                
		try {
                    
		conexion = Conexion.getInstance().getConnection();
		ptsmt = conexion.prepareStatement(SQL_AUTOR_FIND_BY_EMAIL_AND_NOMBRE);
		ptsmt.setString(1, email);
                ptsmt.setString(2, nombre);
		rs = ptsmt.executeQuery();
		System.out.println("!!!!!");
		if(rs.next()) {
		    try {
                      autor = new Autor();
		      autor.setId(rs.getInt("id"));
		      autor.setNombre(rs.getString("nombre"));
                      autor.setEmail(rs.getString("email"));
                      autor.setPassword(rs.getString("password"));
                      autor.setIs_activo(rs.getBoolean("is_activo"));
                      autor.setAvatar(rs.getString("avatar"));
                      
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		}
		} finally {
		try {
		    rs.close();
		} finally {
		    try {
		        ptsmt.close();
		    } finally {
		        conexion.close();
		    }
		}
		}
		return autor;

    }
     public Autor buscarPorEmail(String email) throws ClassNotFoundException, IOException, SQLException {
         Autor autor = null;
		Connection conexion = null;
		PreparedStatement ptsmt = null;
		ResultSet rs = null;
		try {
		conexion = Conexion.getInstance().getConnection();
		ptsmt = conexion.prepareStatement(SQL_AUTOR_FIND_BY_EMAIL);
		ptsmt.setString(1, email);
		rs = ptsmt.executeQuery();
		
		if(rs.next()) {
		    try {
                      autor = new Autor();
		      autor.setId(rs.getInt("id"));
		      autor.setNombre(rs.getString("nombre"));
                      autor.setEmail(rs.getString("email"));
                      autor.setPassword(rs.getString("password"));
                      autor.setIs_activo(rs.getBoolean("is_activo"));
                      autor.setAvatar(rs.getString("avatar"));
                      
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		}
		} finally {
		try {
		    rs.close();
		} finally {
		    try {
		        ptsmt.close();
		    } finally {
		        conexion.close();
		    }
		}
		}
		return autor;

    }
}
