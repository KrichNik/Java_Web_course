package command;

import content.SessionRequestContent;

public interface ActionCommand {

	String execute(SessionRequestContent content);

}
