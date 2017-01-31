package factory;

import java.util.Map;

import command.ActionCommand;
import command.CommandEnum;
import command.EmptyCommand;
import content.SessionRequestContent;
import resource.MessageManager;

public class ActionFactory {

	private static final String COMMAND = "command";

	public ActionCommand defineCommand(SessionRequestContent content) {
		ActionCommand current = new EmptyCommand();

		Map<String, Object> sessionAttributes = content.getSessionAttributes();
		Map<String, String> requestParameters = content.getRequestParameters();
		if (requestParameters.isEmpty()) {
			return current;
		}

		String action = requestParameters.get(COMMAND);

		if (action == null || action.isEmpty()) {
			return current;
		}

		try {
			CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
			current = currentEnum.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			sessionAttributes.put("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
		}

		return current;
	}

}
