
package elpoeta.bitfeather.dao;

import elpoeta.bitfeather.bd.conexion.Conexion;
import elpoeta.bitfeather.modelo.Autor;
import elpoeta.bitfeather.modelo.Categoria;
import elpoeta.bitfeather.modelo.Post;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elpoeta
 */
public class PostDao {
    private static PostDao INSTANCE = null;
    private final static String SQL_POSTS_SELECT = "SELECT * FROM post ORDER BY fecha_creacion DESC;";
    private final static String SQL_POST_SELECT = "SELECT * FROM post WHERE id = ?;";
    private final static String SQL_POST_INSERT = "INSERT INTO post (titulo, sub_titulo, cuerpo, fecha_creacion, categoria_id, autor_id)values(?,?,?,?,?,?);";
    private final static String SQL_POSTS_SELECT_BY_CATEGORIA = "SELECT * FROM post WHERE categoria_id = ? ORDER BY fecha_creacion DESC;"; 
    private final static String SQL_CATEGORIA_SELECT = "SELECT * FROM categoria WHERE id = ?;";
    private final static String SQL_AUTOR_SELECT = "SELECT * FROM autor WHERE id = ?;";
    private final static String SQL_POST_SELECT_BY_AUTOR = "SELECT * FROM post WHERE autor_id = ? ORDER BY fecha_creacion DESC;"; 
    
    private PostDao() throws ClassNotFoundException,
    IOException, SQLException {
}
    public static PostDao getInstance() throws ClassNotFoundException,
	IOException, SQLException {
		if (INSTANCE == null) {
			INSTANCE = new PostDao();
		}
		return INSTANCE;
	}

 
    public Post buscarPorId(Integer id) throws ClassNotFoundException, IOException, SQLException {
      Post post = null;
		Connection conexion = null;
		PreparedStatement ptsmt = null;
		ResultSet rs = null;
                PreparedStatement ptsmtCat = null;
		ResultSet rsCat = null;
                PreparedStatement ptsmtAutor = null;
		ResultSet rsAutor = null;
		try {
		conexion = Conexion.getInstance().getConnection();
		ptsmt = conexion.prepareStatement(SQL_POST_SELECT);
		ptsmt.setInt(1, id);
		rs = ptsmt.executeQuery();
		
		if(rs.next()) {
		    try {
		        post = new Post();
		        post.setId(rs.getInt("id"));
		        post.setTitulo(rs.getString("titulo"));
		        post.setSubTitulo(rs.getString("sub_titulo"));
		        post.setCuerpo(rs.getString("cuerpo"));
                        
                     //   DateTimeFormatter formatoFeha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            //    String formattedDate = rs.getObject("fecha_creacion", LocalDateTime.class).format(formatoFeha);
                              //  LocalDateTime parsedDateTime = LocalDateTime.parse(formattedDate, formatoFeha);
                                post.setFechaCreacion(rs.getObject("fecha_creacion", LocalDateTime.class));
                                
                        
                         ptsmtCat = conexion.prepareStatement(SQL_CATEGORIA_SELECT);
                                ptsmtCat.setInt(1, rs.getInt("categoria_id"));
                                rsCat = ptsmtCat.executeQuery();
                                if(rsCat.next()){
                                Categoria cat = new Categoria();
                                cat.setId(rsCat.getInt("id"));
                                cat.setNombre(rsCat.getString("nombre"));
                                cat.setActiva(rsCat.getBoolean("is_activa"));
                                cat.setImagen(rsCat.getString("imagen"));
                                
                                post.setCategoria(cat);
                                }
                               
                                ptsmtAutor = conexion.prepareStatement(SQL_AUTOR_SELECT);
                                ptsmtAutor.setInt(1, rs.getInt("autor_id"));
                                rsAutor = ptsmtAutor.executeQuery();
                                if(rsAutor.next()){
                                    Autor a = new Autor();
                                    a.setId(rsAutor.getInt("id"));
                                    a.setNombre(rsAutor.getString("nombre"));
                                    a.setEmail(rsAutor.getString("email"));
                                    a.setPassword(rsAutor.getString("password"));
                                    a.setIs_activo(rsAutor.getBoolean("is_activo"));
                                    a.setAvatar(rsAutor.getString("avatar"));
                                    
                                    post.setAutor(a);
                                } 
                        
               
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
		return post;
    }

   
    public List<Post> buscarTodos() throws ClassNotFoundException, IOException, SQLException {
     
        ArrayList<Post> posts = new ArrayList();
     
		Connection conexion = null;
		PreparedStatement ptsmt = null;
		ResultSet rs = null;
                PreparedStatement ptsmtCat = null;
		ResultSet rsCat = null;
                PreparedStatement ptsmtAutor = null;
		ResultSet rsAutor = null;
		try {
			conexion = Conexion.getInstance().getConnection();
			ptsmt = conexion.prepareStatement(SQL_POSTS_SELECT);
			rs = ptsmt.executeQuery();
			Post post = null;
                      
			while (rs.next()) {
			    try {
			        post = new Post();
			        post.setId(rs.getInt("id"));
			        post.setTitulo(rs.getString("titulo"));
			        post.setSubTitulo(rs.getString("sub_titulo"));
			        post.setCuerpo(rs.getString("cuerpo"));
                                post.setFechaCreacion(rs.getObject("fecha_creacion", LocalDateTime.class));
                                Integer idCat = rs.getInt("categoria_id");
                                Integer idAutor = rs.getInt("autor_id");
                               
			        ptsmtCat = conexion.prepareStatement(SQL_CATEGORIA_SELECT);
                                ptsmtCat.setInt(1, idCat);
                                rsCat = ptsmtCat.executeQuery();
                                if(rsCat.next()){
                                Categoria cat = new Categoria();
                                cat.setId(rsCat.getInt("id"));
                                cat.setNombre(rsCat.getString("nombre"));
                                cat.setActiva(rsCat.getBoolean("is_activa"));
                                cat.setImagen(rsCat.getString("imagen"));
                                
                                post.setCategoria(cat);
                                }
                               
                                ptsmtAutor = conexion.prepareStatement(SQL_AUTOR_SELECT);
                                ptsmtAutor.setInt(1, idAutor);
                                rsAutor = ptsmtAutor.executeQuery();
                                if(rsAutor.next()){
                                    Autor a = new Autor();
                                    a.setId(rsAutor.getInt("id"));
                                    a.setNombre(rsAutor.getString("nombre"));
                                    a.setEmail(rsAutor.getString("email"));
                                    a.setPassword(rsAutor.getString("password"));
                                    a.setIs_activo(rsAutor.getBoolean("is_activo"));
                                    a.setAvatar(rsAutor.getString("avatar"));
                                    
                                    post.setAutor(a);
                                } 
                               
			    } catch (Exception ex) {
			        ex.printStackTrace();
			    }
			    posts.add(post);
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
		
		return posts;
    }

 public List<Post> buscarPorCategoria(Integer id) throws ClassNotFoundException, IOException, SQLException {
     
        ArrayList<Post> posts = new ArrayList();
     
		Connection conexion = null;
		PreparedStatement ptsmt = null;
		ResultSet rs = null;
                PreparedStatement ptsmtCat = null;
		ResultSet rsCat = null;
                PreparedStatement ptsmtAutor = null;
		ResultSet rsAutor = null;
		try {
			conexion = Conexion.getInstance().getConnection();
			ptsmt = conexion.prepareStatement(SQL_POSTS_SELECT_BY_CATEGORIA);
                        ptsmt.setInt(1, id);
			rs = ptsmt.executeQuery();
			Post post = null;
                      
			while (rs.next()) {
			    try {
			        post = new Post();
			        post.setId(rs.getInt("id"));
			        post.setTitulo(rs.getString("titulo"));
			        post.setSubTitulo(rs.getString("sub_titulo"));
			        post.setCuerpo(rs.getString("cuerpo"));
                                post.setFechaCreacion(rs.getObject("fecha_creacion", LocalDateTime.class));
                                Integer idCat = rs.getInt("categoria_id");
                                Integer idAutor = rs.getInt("autor_id");
                               
			        ptsmtCat = conexion.prepareStatement(SQL_CATEGORIA_SELECT);
                                ptsmtCat.setInt(1, idCat);
                                rsCat = ptsmtCat.executeQuery();
                                if(rsCat.next()){
                                Categoria cat = new Categoria();
                                cat.setId(rsCat.getInt("id"));
                                cat.setNombre(rsCat.getString("nombre"));
                                cat.setActiva(rsCat.getBoolean("is_activa"));
                                cat.setImagen(rsCat.getString("imagen"));
                                
                                post.setCategoria(cat);
                                }
                               
                                ptsmtAutor = conexion.prepareStatement(SQL_AUTOR_SELECT);
                                ptsmtAutor.setInt(1, idAutor);
                                rsAutor = ptsmtAutor.executeQuery();
                                if(rsAutor.next()){
                                    Autor a = new Autor();
                                    a.setId(rsAutor.getInt("id"));
                                    a.setNombre(rsAutor.getString("nombre"));
                                    a.setEmail(rsAutor.getString("email"));
                                    a.setPassword(rsAutor.getString("password"));
                                    a.setIs_activo(rsAutor.getBoolean("is_activo"));
                                    a.setAvatar(rsAutor.getString("avatar"));
                                    
                                    post.setAutor(a);
                                } 
                               
			    } catch (Exception ex) {
			        ex.printStackTrace();
			    }
			    posts.add(post);
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
		
		return posts;
    }
public List<Post> buscarPorAutor(Integer id) throws ClassNotFoundException, IOException, SQLException {
     
        ArrayList<Post> posts = new ArrayList();
     
		Connection conexion = null;
		PreparedStatement ptsmt = null;
		ResultSet rs = null;
                PreparedStatement ptsmtCat = null;
		ResultSet rsCat = null;
                PreparedStatement ptsmtAutor = null;
		ResultSet rsAutor = null;
		try {
			conexion = Conexion.getInstance().getConnection();
			ptsmt = conexion.prepareStatement(SQL_POST_SELECT_BY_AUTOR);
                        ptsmt.setInt(1, id);
			rs = ptsmt.executeQuery();
			Post post = null;
                      
			while (rs.next()) {
			    try {
			        post = new Post();
			        post.setId(rs.getInt("id"));
			        post.setTitulo(rs.getString("titulo"));
			        post.setSubTitulo(rs.getString("sub_titulo"));
			        post.setCuerpo(rs.getString("cuerpo"));
                                post.setFechaCreacion(rs.getObject("fecha_creacion", LocalDateTime.class));
                                Integer idCat = rs.getInt("categoria_id");
                                Integer idAutor = rs.getInt("autor_id");
                               
			        ptsmtCat = conexion.prepareStatement(SQL_CATEGORIA_SELECT);
                                ptsmtCat.setInt(1, idCat);
                                rsCat = ptsmtCat.executeQuery();
                                if(rsCat.next()){
                                Categoria cat = new Categoria();
                                cat.setId(rsCat.getInt("id"));
                                cat.setNombre(rsCat.getString("nombre"));
                                cat.setActiva(rsCat.getBoolean("is_activa"));
                                cat.setImagen(rsCat.getString("imagen"));
                                
                                post.setCategoria(cat);
                                }
                               
                                ptsmtAutor = conexion.prepareStatement(SQL_AUTOR_SELECT);
                                ptsmtAutor.setInt(1, idAutor);
                                rsAutor = ptsmtAutor.executeQuery();
                                if(rsAutor.next()){
                                    Autor a = new Autor();
                                    a.setId(rsAutor.getInt("id"));
                                    a.setNombre(rsAutor.getString("nombre"));
                                    a.setEmail(rsAutor.getString("email"));
                                    a.setPassword(rsAutor.getString("password"));
                                    a.setIs_activo(rsAutor.getBoolean("is_activo"));
                                    a.setAvatar(rsAutor.getString("avatar"));
                                    
                                    post.setAutor(a);
                                } 
                               
			    } catch (Exception ex) {
			        ex.printStackTrace();
			    }
			    posts.add(post);
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
		
		return posts;
    }
    public void insertar(Post post) throws ClassNotFoundException, IOException, SQLException {
        Connection c = null;
		   PreparedStatement ptsmt = null;
		   try {
                     
		       c = Conexion.getInstance().getConnection();
		       ptsmt = c.prepareStatement(SQL_POST_INSERT);
		                     
                       ptsmt.setString(1, post.getTitulo());
		       ptsmt.setString(2, post.getSubTitulo());
		       ptsmt.setString(3, post.getCuerpo());
                       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                       ptsmt.setObject(4, LocalDateTime.now().format(formatter));
		       ptsmt.setInt(5, post.getCategoria().getId());
		       ptsmt.setInt(6, post.getAutor().getId());
		       ptsmt.execute();
		   } finally {
		       try {
		           ptsmt.close();
		       } finally {
		           c.close();
		       }
		   }
    }

}
          /* 
LocalDate now = LocalDate.now();
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
String formattedDate = now.format(formatter);
LocalDate parsedDate = LocalDate.parse(formattedDate, formatter);
    
    getObject(int, LocalDate.class)

DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String formattedDate = carrito.getFechaCreacion().format(formatter);

*/