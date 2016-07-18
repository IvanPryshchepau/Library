package by.tr.library.dao.impl;

import by.tr.library.dao.CommonDao;
import by.tr.library.dao.exception.ConnectionPoolException;
import by.tr.library.dao.exception.DAOException;
import by.tr.library.dao.pool.ConnectionPool;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLCommonDao implements CommonDao {

	@Override
	public boolean authorization(String login, String password) throws DAOException {
		try {
			ConnectionPool.PooledConnection connection =
                    (ConnectionPool.PooledConnection) ConnectionPool.connectionPool.takeConnection();
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM users");

			while (rs.next()){
				if (login.equals(rs.getString(2)) && password.equals(rs.getString(3)) &&
						!rs.getString(4).equals("blocked")){
					ConnectionPool.connectionPool.returnConnection(connection);
					return true;
				}
			}
			ConnectionPool.connectionPool.returnConnection(connection);
			return false;
		} catch (ConnectionPoolException e) {
			throw new DAOException("error taking connection");
		} catch (SQLException e) {
			throw new DAOException("error edit table");
		}
	}

}
