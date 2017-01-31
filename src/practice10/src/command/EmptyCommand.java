package command;

import content.SessionRequestContent;
import resource.ConfigurationManager;

public class EmptyCommand implements ActionCommand {

	@Override
	public String execute(SessionRequestContent content) {
		String page = ConfigurationManager.getProperty("path.page.login");
		return page;
	}

}
