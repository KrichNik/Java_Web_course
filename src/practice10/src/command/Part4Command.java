package command;

import java.util.Map;

import content.SessionRequestContent;
import exception.LoginException;
import logic.part4.Part4;
import resource.ConfigurationManager;

public class Part4Command implements ActionCommand {

	@Override
	public String execute(SessionRequestContent content) {
		String page = null;

		Map<String, Object> requestAttributes = content.getRequestAttributes();
		Map<String, String> requestParameters = content.getRequestParameters();
		Map<String, Object> sessionAttributes = content.getSessionAttributes();

		try {
			if (requestParameters.isEmpty()) {
				System.out.println("requestParameters empty");
				throw new LoginException("Input new name!");
			}

			if (sessionAttributes.isEmpty()) {
				System.out.println("sessionAttributes empty");
				throw new LoginException("Login error!");
			}

			String newName = requestParameters.get("newName");
			if (newName == null || newName.isEmpty()) {
				throw new LoginException("Input new name!");
			}

			String login = (String) sessionAttributes.get("login");
			if (login == null || login.isEmpty()) {
				throw new LoginException("Login error!");
			}

			Part4.changeUserName(login, newName);

			sessionAttributes.put("name", newName);
		} catch (LoginException e) {
			System.out.println(e.getMessage());
			requestAttributes.put("wrongAction", e.getMessage());
		}

		page = ConfigurationManager.getProperty("path.page.part4");
		return page;
	}

}
