package command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import content.SessionRequestContent;
import resource.ConfigurationManager;

public class Part3Command implements ActionCommand {

	@SuppressWarnings("unchecked")
	@Override
	public String execute(SessionRequestContent content) {
		String page = null;

		Map<String, Object> sessionAttributes = content.getSessionAttributes();
		Map<String, String> requestParameters = content.getRequestParameters();

		String userName = (String) requestParameters.get("userName");

		boolean isRemove = false;
		if (requestParameters.get("isRemove") != null) {
			isRemove = true;
		}

		List<String> localSessionUsers = (List<String>) sessionAttributes.get("userNames");
		if (localSessionUsers == null || isRemove) {
			localSessionUsers = new ArrayList<>();
		}

		if (userName != null) {
			localSessionUsers.add(userName);
		}

		sessionAttributes.put("userNames", localSessionUsers);
		page = ConfigurationManager.getProperty("path.page.part3");
		return page;
	}

}
