
package elpoeta.bitfeather.web.server;

import elpoeta.bitfeather.dao.PostDao;
import elpoeta.bitfeather.modelo.Post;
import static elpoeta.bitfeather.util.GsonUtil.CONVERTIR;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author elpoeta
 */
@WebServlet(name = "PostServer", urlPatterns = {"/api/PostServer"})
public class PostServer extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
 
        try {
            TreeMap parametro = CONVERTIR.fromJson(request.getParameter("q"), TreeMap.class); 
           String resultado=null;
             if(parametro.containsKey("categoria")){
                Integer id = Integer.parseInt( String.valueOf( parametro.get("categoria")));
       
                  if(id == 0){
                        List<Post> listado =  PostDao.getInstance().buscarTodos();
                         resultado = CONVERTIR.toJson(listado); 
                        
                    }else{
                    
                      List<Post> listado =  PostDao.getInstance().buscarPorCategoria(id);
                         resultado = CONVERTIR.toJson(listado); 
                        
                    }
                 }
                  else if(parametro.containsKey("postid")){
                       Integer id = Integer.parseInt( String.valueOf( parametro.get("postid")));
                        Post post = PostDao.getInstance().buscarPorId(id);
                        resultado = CONVERTIR.toJson(post); 
                        
                     }
                  else{
                 out.println("Error");
             }
             out.println("" + resultado);
        } catch (ClassNotFoundException ex) {
            out.println("Verificar:" + ex.getMessage());
        } catch (SQLException ex) {
            out.println("Verificar:" + ex.getMessage());
        } catch (Exception ex) {
            out.println("Verificar:" + ex.getMessage());
        } finally {
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
    }

   
}
