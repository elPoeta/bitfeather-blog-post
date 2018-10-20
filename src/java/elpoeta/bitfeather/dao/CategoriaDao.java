
package elpoeta.bitfeather.dao;

import elpoeta.bitfeather.bd.conexion.Conexion;
import elpoeta.bitfeather.modelo.Categoria;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elpoeta
 */
public class CategoriaDao {
    private static CategoriaDao INSTANCE = null;
    private final static String SQL_CATEGORIAS_SELECT = "SELECT * FROM categoria;";
    private final static String SQL_CATEGORIA_SELECT = "SELECT * FROM categoria WHERE id = ?;";
    
    private CategoriaDao() throws ClassNotFoundException,
    IOException, SQLException {
}
    public static CategoriaDao getInstance() throws ClassNotFoundException,
	IOException, SQLException {
		if (INSTANCE == null) {
			INSTANCE = new CategoriaDao();
		}
		return INSTANCE;
	}

    public Categoria buscarPorId(Integer id) throws ClassNotFoundException,
		IOException, SQLException {
        Categoria cat = null;
		Connection conexion = null;
		PreparedStatement ptsmt = null;
		ResultSet rs = null;
		try {
		conexion = Conexion.getInstance().getConnection();
		ptsmt = conexion.prepareStatement(SQL_CATEGORIA_SELECT);
		ptsmt.setInt(1, id);
		rs = ptsmt.executeQuery();
		
		if(rs.next()) {
		    try {
		        cat = new Categoria();
		        cat.setId(rs.getInt("id"));
		        cat.setNombre(rs.getString("nombre"));
                        cat.setImagen(rs.getString("imagen"));
		        cat.setActiva(rs.getBoolean("is_activa"));
		     
		 
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
		return cat;

    }

   
    public List<Categoria> buscarTodos() throws ClassNotFoundException, IOException, SQLException {
        ArrayList<Categoria> lista = new ArrayList();
		Connection conexion = null;
		PreparedStatement ptsmt = null;
		ResultSet rs = null;
		try {
		conexion = Conexion.getInstance().getConnection();
		ptsmt = conexion.prepareStatement(SQL_CATEGORIAS_SELECT);
		rs = ptsmt.executeQuery();
		Categoria cat = null;
		while (rs.next()) {
		    try {
		        cat = new Categoria();
		        cat.setId(rs.getInt("id"));
		        cat.setNombre(rs.getString("nombre"));
                        cat.setImagen(rs.getString("imagen"));
		        cat.setActiva(rs.getBoolean("is_activa"));
		 
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		    lista.add(cat);
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
		return lista;
    }

}
