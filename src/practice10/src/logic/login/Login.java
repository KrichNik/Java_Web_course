package logic.login;

import java.sql.Connection;

import db.Connector;
import db.dao.RoleDAO;
import db.dao.UserDAO;
import db.entities.Role;
import db.entities.User;

public class Login {

	public static User getUser(String enterLogin, String enterPassword) {
		Connection connection = Connector.getConnection();
		User user = null;
		try {
			UserDAO userDAO = new UserDAO(connection);
			user = userDAO.findUserByLogin(enterLogin, enterPassword);
		} finally {
			Connector.close(connection);
		}
		return user;
	}

	public static Role getRole(User user) {
		Connection connection = Connector.getConnection();
		Role role = null;
		try {
			RoleDAO roleDAO = new RoleDAO(connection);
			role = roleDAO.findRoleById(user.getRoleId());
		} finally {
			Connector.close(connection);
		}
		return role;
	}

}
