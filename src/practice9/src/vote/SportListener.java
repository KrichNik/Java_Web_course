package vote;

import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebListener;
 
@WebListener
public class SportListener implements ServletContextListener {
	
    public void contextInitialized(ServletContextEvent sce) {
    	
        ServletContext sc = sce.getServletContext();
        List<String> listSport = Arrays.asList(sc.getInitParameter("listSport").split(" "));
        
        sc.setAttribute("listSport", listSport);
        
    }
     
    public void contextDestroyed(ServletContextEvent sce) {
    	
    } 

}
