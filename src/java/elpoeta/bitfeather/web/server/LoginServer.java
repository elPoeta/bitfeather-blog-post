
package elpoeta.bitfeather.web.server;

import elpoeta.bitfeather.dao.AutorDao;
import elpoeta.bitfeather.modelo.Autor;
import elpoeta.bitfeather.util.GsonUtil;
import static elpoeta.bitfeather.util.GsonUtil.CONVERTIR;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author elpoeta
 */
@WebServlet(name = "LoginServer", urlPatterns = {"/api/LoginServer"})
public class LoginServer extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           Autor autor = (Autor) request.getSession().getAttribute("autor");
        if( autor != null ){
            
            response.getWriter().print(CONVERTIR.toJson(autor));
        }else{
            response.getWriter().print(CONVERTIR.toJson("error"));
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        	
            String texto = request.getReader().readLine();
            
            Autor autorParametro = CONVERTIR.fromJson(texto, Autor.class);
            System.out.println("PaR > "+autorParametro);
            Autor autorBD = AutorDao.getInstance().buscarPorEmail(autorParametro.getEmail()); 
            System.out.println("BD > "+autorBD);
        
            if(autorBD.getEmail().equals(autorParametro.getEmail()) && BCrypt.checkpw(autorParametro.getPassword(), autorBD.getPassword()) && autorBD.isIs_activo())
            {   
                request.getSession().setAttribute("autor", autorBD);
               System.out.println("...  logIN ..." + request.getSession().getAttribute("autor") );
               out.print(CONVERTIR.toJson("ok"));
             //out.println(CONVERTIR.toJson(autorBD));
               
            }else{
                request.getSession().removeAttribute("autor");
               System.out.println("...  logOUT ..." + request.getSession().getAttribute("autor") );
                out.print( CONVERTIR.toJson( "error"));
                //out.println(CONVERTIR.toJson("error"));
            
            }

        } catch (ClassNotFoundException ex) {
            out.println("Verificar: " + ex.getMessage());
            System.out.println("Class > "+ex.getMessage());
        } catch (SQLException ex) {
            out.println("Verificar:" + ex.getMessage());
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            out.println(ex.getMessage());
            System.out.println("EXcep > "+ex.getMessage());
        } finally {
            out.close();
        }
       
    }

}
