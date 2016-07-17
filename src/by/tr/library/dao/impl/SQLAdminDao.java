package by.tr.library.dao.impl;

import by.tr.library.bean.Book;
import by.tr.library.dao.AdminDao;
import by.tr.library.dao.exception.ConnectionPoolException;
import by.tr.library.dao.exception.DAOException;
import by.tr.library.dao.pool.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLAdminDao implements AdminDao {

	@Override
	public void blockUser(String login) throws DAOException {
		try {
			ConnectionPool.PooledConnection connection =
					(ConnectionPool.PooledConnection) ConnectionPool.connectionPool.takeConnection();
			Statement s = connection.createStatement();

			s.executeUpdate("UPDATE connpool.users SET status=blocked WHERE login=" + login);
			ConnectionPool.connectionPool.returnConnection(connection);
		} catch (ConnectionPoolException e) {
			throw new DAOException("error taking connection");
		} catch (SQLException e) {
			throw new DAOException("error edit table");
		}
	}

	@Override
	public void addBook(Book book) throws DAOException {
		String sql = "INSERT INTO connpool.books(title, price, available) VALUES(?,?,?)";
		editBookTable(sql, book);
	}

	@Override
	public void deleteBook(Book book) throws DAOException {
		String sql = "DELETE FROM connpool.books WHERE(title=?,price=?,available=?)";
		editBookTable(sql, book);
	}

	public void editBookTable(String sql, Book book) throws DAOException {
		try {
			ConnectionPool.PooledConnection connection =
					(ConnectionPool.PooledConnection) ConnectionPool.connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, book.getTitle());
			preparedStatement.setInt(2, book.getPrice());
			preparedStatement.setString(3, book.getAvailable());
			preparedStatement.executeUpdate();
			ConnectionPool.connectionPool.returnConnection(connection);
		} catch (ConnectionPoolException e) {
			throw new DAOException("error taking connection");
		} catch (SQLException e) {
			throw new DAOException("error edit table");
		}
	}
}
