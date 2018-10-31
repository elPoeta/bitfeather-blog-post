
package elpoeta.bitfeather.web.server;

import elpoeta.bitfeather.dao.PostDao;
import elpoeta.bitfeather.modelo.Autor;
import elpoeta.bitfeather.modelo.Post;
import static elpoeta.bitfeather.util.GsonUtil.CONVERTIR;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author elpoeta
 */
@WebServlet(name = "AutorPostServer", urlPatterns = {"/api/privado/AutorPostServer"})
public class AutorPostServer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
 
           
	         try {
                     Autor autor =(Autor) req.getSession().getAttribute("autor");
	             List<Post> listado =  PostDao.getInstance().buscarPorAutor(autor.getId());
	             String resultado = CONVERTIR.toJson(listado);
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }


}
