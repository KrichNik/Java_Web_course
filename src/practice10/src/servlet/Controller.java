package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.ActionCommand;
import content.SessionRequestContent;
import factory.ActionFactory;
import resource.ConfigurationManager;
import resource.MessageManager;

@WebServlet("/controller")
public class Controller extends HttpServlet {

	private static final long serialVersionUID = -5538458263576826384L;

	private static final String ENCODING = "UTF-8";

	private static final String CONTENT_TYPE = "text/html";

	public Controller() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType(CONTENT_TYPE);
		response.setCharacterEncoding(ENCODING);
		request.setCharacterEncoding(ENCODING);

		String page = null;

		ActionFactory actionFactory = new ActionFactory();
		SessionRequestContent content = new SessionRequestContent(request);
		ActionCommand command = actionFactory.defineCommand(content);

		page = command.execute(content);

		content.insertAttributes();

		if (page != null) {
			request.getRequestDispatcher(page).forward(request, response);
		} else {
			page = ConfigurationManager.getProperty("path.page.index");
			request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
			response.sendRedirect(request.getContextPath() + page);
		}
	}

}
