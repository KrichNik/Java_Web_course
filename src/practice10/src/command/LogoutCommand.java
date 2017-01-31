package command;

import content.SessionRequestContent;
import resource.ConfigurationManager;

public class LogoutCommand implements ActionCommand {

	@Override
	public String execute(SessionRequestContent content) {
		String page = ConfigurationManager.getProperty("path.page.index");
		content.clearSession();
		return page;
	}

}
