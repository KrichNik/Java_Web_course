package command;

public enum CommandEnum {

	LOGIN {
		{
			this.command = new LoginCommand();
		}
	},

	LOGOUT {
		{
			this.command = new LogoutCommand();
		}
	},

	PART3 {
		{
			this.command = new Part3Command();
		}
	},

	PART4 {
		{
			this.command = new Part4Command();
		}
	};

	ActionCommand command;

	public ActionCommand getCurrentCommand() {
		return command;
	}

}
