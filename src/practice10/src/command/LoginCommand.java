package command;

import java.util.Map;

import content.SessionRequestContent;
import db.entities.User;
import exception.LoginException;
import logic.login.Login;
import resource.ConfigurationManager;
import resource.MessageManager;

public class LoginCommand implements ActionCommand {

	private static final String PARAM_NAME_LOGIN = "login";

	private static final String PARAM_NAME_PASSWORD = "password";

	@Override
	public String execute(SessionRequestContent content) {
		String page = null;

		Map<String, Object> requestAttributes = content.getRequestAttributes();
		Map<String, Object> sessionAttributes = content.getSessionAttributes();
		Map<String, String> requestParameters = content.getRequestParameters();

		try {
			if (requestParameters.isEmpty()) {
				throw new LoginException("No parameters!");
			}

			String enterLogin = requestParameters.get(PARAM_NAME_LOGIN);
			if (enterLogin == null || enterLogin.isEmpty()) {
				throw new LoginException("No login!");
			}

			String enterPassword = requestParameters.get(PARAM_NAME_PASSWORD);
			if (enterPassword == null || enterPassword.isEmpty()) {
				throw new LoginException("No password!");
			}

			User user = Login.getUser(enterLogin, enterPassword);

			String role = Login.getRole(user).getName();

			sessionAttributes.put("login", user.getLogin());
			sessionAttributes.put("role", role);
			sessionAttributes.put("name", user.getName());

			page = ConfigurationManager.getProperty("path.page.part3");
		} catch (LoginException e) {
			System.out.println(e.getMessage());
			requestAttributes.put("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
			page = ConfigurationManager.getProperty("path.page.login");
		}

		return page;
	}

}
