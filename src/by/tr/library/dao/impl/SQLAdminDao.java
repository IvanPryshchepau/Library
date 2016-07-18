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
		String sql = "UPDATE library.users SET status=? WHERE login=?";
		String status = "blocked";
		try {
			ConnectionPool.PooledConnection connection =
					(ConnectionPool.PooledConnection) ConnectionPool.connectionPool.takeConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, status);
			ps.setString(2, login);
			ps.executeUpdate();
			ConnectionPool.connectionPool.returnConnection(connection);
		} catch (ConnectionPoolException e) {
			throw new DAOException("error taking connection");
		} catch (SQLException e) {
			throw new DAOException("error edit table");
		}
	}

	@Override
	public void addBook(Book book) throws DAOException {
		String sql = "INSERT INTO library.books(title, price, available) VALUES(?,?,?)";
		editBookTable(sql, book);
	}

	@Override
	public void deleteBook(Book book) throws DAOException {
		String sql = "DELETE FROM library.books WHERE title=? AND price=? AND available=?";
		editBookTable(sql, book);
	}

	private void editBookTable(String sql, Book book) throws DAOException {
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
