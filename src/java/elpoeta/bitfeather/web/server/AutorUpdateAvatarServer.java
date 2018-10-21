
package elpoeta.bitfeather.web.server;

import elpoeta.bitfeather.dao.AutorDao;
import elpoeta.bitfeather.modelo.Autor;
import static elpoeta.bitfeather.util.GsonUtil.CONVERTIR;
import elpoeta.bitfeather.util.Validar;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "AutorUpdateAvatarServer", urlPatterns = {"/api/privado/AutorUpdateAvatarServer"})
public class AutorUpdateAvatarServer extends HttpServlet {
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
            
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
             request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
           
            String texto = request.getReader().readLine();
 
            Autor autor = (Autor)request.getSession().getAttribute("autor");
            TreeMap avatar = CONVERTIR.fromJson(texto, TreeMap.class);
            String img = (String) avatar.get("avatar");
            if(validarAvatar(img)){
                   AutorDao.getInstance().updateAvatar(autor.getId(), img);
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
    

  private boolean validarAvatar(String img){
    if(!Validar.isNullOEspaciosEnBlanco(img)) 
      {
        return true;
    }
 
      return false;
    } 


}
