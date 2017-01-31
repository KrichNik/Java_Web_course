package vote;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SportServlet")
public class SportServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Map<String, Integer> mapSportAndUserNames = new HashMap<>();

	public SportServlet() {

	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		ServletContext sc = getServletContext();
		List<String> allSportParams = (List<String>) sc.getAttribute("listSport");

		for (String param : allSportParams) {
			if (!mapSportAndUserNames.containsKey(param)) {
				mapSportAndUserNames.put(param, 0);
			}
		}

		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet CalcServlet</title>");
		out.println("</head>");
		out.println("<body>");

		try {
			HttpSession session = request.getSession();

			String userName = request.getParameter("userName");
			String sportParam = request.getParameter("listSport");
			if (userName.length() == 0) {
				throw new IllegalArgumentException("Введите имя!");
			}

			List<String> localSessionUsers;
			if (session.isNew()) {
				localSessionUsers = new ArrayList<>();
			} else {
				localSessionUsers = (ArrayList<String>) session.getAttribute("users");
				if (localSessionUsers.contains(userName)) {
					throw new IllegalArgumentException("Невозможно повторное голосование!");
				}
			}
			localSessionUsers.add(userName);
			session.setAttribute("users", localSessionUsers);

			for (Map.Entry<String, Integer> pair : mapSportAndUserNames.entrySet()) {
				if (pair.getKey().equals(sportParam)) {
					pair.setValue(pair.getValue() + 1);
				}
			}

			out.println("<table>");
			for (Map.Entry<String, Integer> pair : mapSportAndUserNames.entrySet()) {
				String sport = pair.getKey();
				int count = pair.getValue();
				out.println("<tr>");
				out.println("<td>" + sport + "</td>");
				out.println("<td>" + count + "</td>");
				out.println("</tr>");
			}
			out.println("</table>");
		} catch (Exception e) {
			out.println("<p>" + e.getMessage() + "</p>");
		} finally {
			out.println("<a href = \"http://localhost:8080/Practice9/vote\">Назад</a><br />");
			out.println("<a href = \"http://localhost:8080/Practice9/\">На главную</a>");
			out.println("</body>");
			out.println("</html>");
			out.close();
		}
	}

}
