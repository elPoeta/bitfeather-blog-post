
package elpoeta.bitfeather.web.server;

import elpoeta.bitfeather.dao.AutorDao;
import elpoeta.bitfeather.modelo.Autor;
import elpoeta.bitfeather.util.GsonUtil;
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
@WebServlet(name = "RegistroServer", urlPatterns = {"/api/RegistroServer"})
public class RegistroServer extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
      
        try {
        	
            String texto = request.getReader().readLine();

            Autor autorParametro = CONVERTIR.fromJson(texto, Autor.class); 
            System.out.println(autorParametro.getNombre());
            System.out.println("VALID "+validarUsuario(autorParametro));
            if(validarUsuario(autorParametro)){
                autorParametro.setNombre("@"+autorParametro.getNombre());
               System.out.println(autorParametro.getNombre());
                Autor autorBD = AutorDao.getInstance().buscarPorEmailAndNombre(autorParametro.getEmail(), autorParametro.getNombre());  
            System.out.println("BDAUTOR>> "+autorBD);
            if( autorBD == null && autorParametro.getPassword().equals(autorParametro.getConfirmPassword()))
            {    System.out.println("ANTES INSSERTAR!!!!");
                AutorDao.getInstance().insertar(autorParametro);
                out.println(GsonUtil.CONVERTIR.toJson("OK"));
            }else{
                  System.out.println("Error");
                out.println(GsonUtil.CONVERTIR.toJson("error"));
            }
            }else{
                  System.out.println("Error");
                out.println(GsonUtil.CONVERTIR.toJson("error"));
            
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

  private boolean validarUsuario(Autor a){
    if(!Validar.isNullOEspaciosEnBlanco(a.getNombre())
       && Validar.isValidEmail(a.getEmail()) 
       && !Validar.isNullOEspaciosEnBlanco(a.getPassword())  
       && !Validar.isNullOEspaciosEnBlanco(a.getConfirmPassword())){
        return true;
    }
      
      return false;
    }


}
