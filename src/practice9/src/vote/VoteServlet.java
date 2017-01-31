package vote;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/VoteServlet")
public class VoteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public VoteServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ServletContext sc = getServletContext();
		Object listSport = sc.getAttribute("listSport");

		request.setAttribute("listSport", listSport);
		request.getRequestDispatcher("Vote.jsp").forward(request, response);


	}

}
