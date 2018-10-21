
package elpoeta.bitfeather.web.server;

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
@WebServlet(name = "Logout", urlPatterns = {"/api/privado/Logout"})
public class Logout extends HttpServlet {

      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
           request.getSession().setAttribute("autor",null);
           response.getWriter().print(CONVERTIR.toJson("ok"));
      
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
    }

}
