
package elpoeta.bitfeather.web.server;

import elpoeta.bitfeather.dao.PostDao;
import elpoeta.bitfeather.modelo.Autor;
import elpoeta.bitfeather.modelo.Post;
import static elpoeta.bitfeather.util.GsonUtil.CONVERTIR;
import elpoeta.bitfeather.util.Validar;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author elpoeta
 */
@WebServlet(name = "CrearPostServer", urlPatterns = {"/api/privado/CrearPostServer"})
public class CrearPostServer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         String resultado = CONVERTIR.toJson("OK");
            out.println("" +resultado);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             response.setContentType("text/html;charset=UTF-8");
             request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            String texto = request.getReader().readLine();
 
            Autor autor = (Autor)request.getSession().getAttribute("autor");
            Post postParametro = CONVERTIR.fromJson(texto, Post.class);
     
            if(validarPost(postParametro)){
                 postParametro.setAutor(autor);
            PostDao.getInstance().insertar(postParametro);
            out.println(CONVERTIR.toJson("OK"));
            }else{
                throw  new IllegalArgumentException("post no valido");
               
            }
           

        } catch (ClassNotFoundException ex) {
            out.println("Verificar: " + ex.getMessage());
            System.out.println("Class > "+ex.getMessage());
        } catch (SQLException ex) {
            out.println("Verificar:" + ex.getMessage());
            System.out.println("SQL > "+ex.getMessage());
        } catch (Exception ex) {
            out.println("Verificar:" + ex.getMessage());
            System.out.println("EXcep > "+ex.getMessage());
        } finally {
            out.close();
        }
    }

  private boolean validarPost(Post p){
    if(!Validar.isNullOEspaciosEnBlanco(p.getTitulo()) 
       && !Validar.isNullOEspaciosEnBlanco(p.getSubTitulo())  
       && !Validar.isNullOEspaciosEnBlanco(p.getCuerpo())){
        return true;
    }
      
      return false;
    }
}
