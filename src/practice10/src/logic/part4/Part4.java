package logic.part4;

import java.sql.Connection;

import db.Connector;
import db.dao.UserDAO;
import exception.LoginException;

public class Part4 {

	public static void changeUserName(String login, String newName) {
		Connection connection = Connector.getConnection();
		UserDAO userDAO = new UserDAO(connection);
		if (!userDAO.updateUserName(login, newName)) {
			throw new LoginException();
		}
	}
}
