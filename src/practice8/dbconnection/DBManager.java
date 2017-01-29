package practice8.dbconnection;

import practice8.entities.Group;
import practice8.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

	private static DBManager instance;

	private DBManager() {

	}

	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private static final String INSERT_USER = "insert into users (login) values (?);";

	public void insertUser(User user) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnector.getConnection();
			statement = connection.prepareStatement(INSERT_USER);
			statement.setString(1, user.getLogin());
			if (statement.executeUpdate() == 0) {
				System.out.println("group has not been added!");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeStatement(statement);
			DBConnector.closeConnection(connection);
		}
	}

	private static final String SELECT_USER_BY_LOGIN = "select * from users where login = ?;";

	public User getUser(String login) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnector.getConnection();
			try {
				User user = new User();
				statement = connection.prepareStatement(SELECT_USER_BY_LOGIN);
				statement.setString(1, login);
				ResultSet resSet = statement.executeQuery();
				if (resSet.next()) {
					user.setId(resSet.getInt("id"));
					user.setLogin(resSet.getString("login"));
				}
				return user;
			} catch (SQLException e) {
				System.out.println("user has not been created! " + e.getMessage());
				return null;
			}
		} finally {
			closeStatement(statement);
			DBConnector.closeConnection(connection);
		}
	}

	private static final String SELECT_ALL_USERS = "select * from users;";

	public List<User> findAllUsers() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DBConnector.getConnection();
			List<User> userList = new ArrayList<>();
			try {
				statement = connection.createStatement();
				resultSet = statement.executeQuery(SELECT_ALL_USERS);
				User user = null;
				while (resultSet.next()) {
					user = new User();
					user.setId(resultSet.getInt(1));
					user.setLogin(resultSet.getString(2));
					userList.add(user);
				}
				return userList;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("no one user was added!");
			return userList;
		} finally {
			closeResultSet(resultSet);
			closeStatement(statement);
			DBConnector.closeConnection(connection);
		}
	}

	private static final String INSERT_GROUP = "insert into groups (name) values (?);";

	public void insertGroup(Group group) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnector.getConnection();
			try {
				statement = connection.prepareCall(INSERT_GROUP);
				statement.setString(1, group.getName());
				statement.execute();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} finally {
			closeStatement(statement);
			DBConnector.closeConnection(connection);
		}
	}

	private static final String SELECT_GROUP_BY_NAME = "select * from groups where name = ?";

	public Group getGroup(String name) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnector.getConnection();
			try {
				Group group = new Group();
				statement = connection.prepareStatement(SELECT_GROUP_BY_NAME);
				statement.setString(1, name);
				ResultSet resSet = statement.executeQuery();
				if (resSet.next()) {
					group.setId(resSet.getInt("id"));
					group.setName(resSet.getString("name"));
				}
				return group;
			} catch (SQLException e) {
				System.out.println("group has not been created! " + e.getMessage());
				return null;
			}
		} finally {
			closeStatement(statement);
			DBConnector.closeConnection(connection);
		}
	}

	private static final String SELECT_ALL_GROUPS = "select * from groups;";

	public List<Group> findAllGroups() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DBConnector.getConnection();
			List<Group> groupList = new ArrayList<>();
			try {
				statement = connection.createStatement();
				resultSet = statement.executeQuery(SELECT_ALL_GROUPS);
				Group group = null;
				while (resultSet.next()) {
					group = new Group();
					group.setId(resultSet.getInt(1));
					group.setName(resultSet.getString(2));
					groupList.add(group);
				}
				return groupList;
			} catch (SQLException e) {
				System.out.println("no one group was added! " + e.getMessage());
				return groupList;
			}
		} finally {
			closeResultSet(resultSet);
			closeStatement(statement);
			DBConnector.closeConnection(connection);
		}
	}

	private static final String INSERT_USER_GROUPS = "insert into user_group values (default, ?, ?);";

	public void setGroupsForUser(User user, Group... groups) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnector.getConnection();

			// transaction
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(INSERT_USER_GROUPS);
			statement.setInt(1, user.getId());
			for (Group group : groups) {
				statement.setInt(2, group.getId());
				statement.execute();
			}
			connection.commit();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				connection.rollback();
			} catch (SQLException ex) {
				System.out.println(ex);
			}
		} finally {
			closeStatement(statement);
			DBConnector.closeConnection(connection);
		}
	}

	private static final String SELECT_GROUPS_BY_USER = "select * from groups " + "inner join user_group "
			+ "on user_group.group_id = groups.id " + "where user_group.user_id = ?;";

	public List<Group> getUserGroups(User user) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DBConnector.getConnection();
			List<Group> groupList = new ArrayList<>();
			try {
				statement = connection.prepareStatement(SELECT_GROUPS_BY_USER);
				statement.setInt(1, user.getId());
				resultSet = statement.executeQuery();
				Group group = null;
				while (resultSet.next()) {
					group = new Group();
					group.setId(resultSet.getInt(1));
					group.setName(resultSet.getString(2));
					groupList.add(group);
				}
				return groupList;
			} catch (SQLException e) {
				System.out.println("no one group was found! " + e.getMessage());
				return groupList;
			}
		} finally {
			closeResultSet(resultSet);
			closeStatement(statement);
			DBConnector.closeConnection(connection);
		}
	}

	private static final String DELETE_GROUP = "delete from groups where id = ? and name = ?;";

	public void deleteGroup(Group group) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnector.getConnection();
			statement = connection.prepareStatement(DELETE_GROUP);
			statement.setInt(1, group.getId());
			statement.setString(2, group.getName());
			if (statement.executeUpdate() == 0) {
				System.out.println("group has not been deleted!");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeStatement(statement);
			DBConnector.closeConnection(connection);
		}
	}

	private static final String UPDATE_GROUP_NAME = "update groups set name = ? where id = ?";

	public void updateGroup(Group group) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnector.getConnection();
			statement = connection.prepareStatement(UPDATE_GROUP_NAME);
			statement.setString(1, group.getName());
			statement.setInt(2, group.getId());
			if (statement.executeUpdate() == 0) {
				System.out.println("group has not been updated!");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeStatement(statement);
			DBConnector.closeConnection(connection);
		}
	}

	/*****************************************************
	 * Close methods
	 *****************************************************/

	private void closeStatement(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void closeResultSet(ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
