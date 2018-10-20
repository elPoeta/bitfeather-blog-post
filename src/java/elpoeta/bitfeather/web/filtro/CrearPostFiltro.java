
package elpoeta.bitfeather.web.filtro;

import elpoeta.bitfeather.modelo.Autor;
import elpoeta.bitfeather.util.GsonUtil;
import static elpoeta.bitfeather.util.GsonUtil.CONVERTIR;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author elpoeta
 */
@WebFilter(filterName = "CrearPostFiltro", urlPatterns = {"/api/privado/*"})
public class CrearPostFiltro implements Filter {
    
      @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
          System.out.println("... CrearPostFiltro ...............................");
        Autor autorActual = (Autor)((HttpServletRequest)request).getSession().getAttribute("autor");
        if ( autorActual !=null ) { 
            System.out.println("...OK PrivadoFiltra ............................... " );
            chain.doFilter(request, response);
            
        } else {
            System.out.println("...UPSS PrivadoFiltra ...............................");
            response.getWriter().print(CONVERTIR.toJson("error"));
            //request.getRequestDispatcher("../login.html").forward(request, response);
        }
    }

    @Override
    public void destroy() {
      
    }
    
}
