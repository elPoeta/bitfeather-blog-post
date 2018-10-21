
package elpoeta.bitfeather.web.server;

import elpoeta.bitfeather.modelo.Autor;
import static elpoeta.bitfeather.util.GsonUtil.CONVERTIR;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author elpoeta
 */
@WebServlet(name = "MiCuentaServer", urlPatterns = {"/api/privado/MiCuentaServer"})
public class MiCuentaServer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
	         PrintWriter out = response.getWriter();
     
	            Autor autor =  (Autor) request.getSession().getAttribute("autor");
	             String resultado = CONVERTIR.toJson(autor);
	             out.println("" + resultado);

	       
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
