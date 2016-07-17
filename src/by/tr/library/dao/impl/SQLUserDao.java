package by.tr.library.dao.impl;

import by.tr.library.bean.Book;
import by.tr.library.dao.UserDao;
import by.tr.library.dao.exception.ConnectionPoolException;
import by.tr.library.dao.exception.DAOException;
import by.tr.library.dao.pool.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLUserDao implements UserDao{

	@Override
	public List<Book> getCatalog() throws DAOException {
		ArrayList<Book> list = new ArrayList<Book>();
		try {
			ConnectionPool.PooledConnection connection =
					(ConnectionPool.PooledConnection) ConnectionPool.connectionPool.takeConnection();
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM books");
			while (rs.next()){
				Book book = new Book();
				book.setTitle(rs.getString(1));
				book.setPrice(rs.getInt(2));
				book.setAvailable(rs.getString(3));
				list.add(book);
			}
			ConnectionPool.connectionPool.returnConnection(connection);
		} catch (ConnectionPoolException e) {
			throw new DAOException("error taking connection");
		} catch (SQLException e) {
			throw new DAOException("error edit table");
		}
		return list;
	}

	@Override
	public void registerUser(String login, String password) throws DAOException {

		String sql = "INSERT INTO connpool.users(login, password, status) VALUES(?,?,user)";
		try {
			ConnectionPool.PooledConnection connection =
					(ConnectionPool.PooledConnection) ConnectionPool.connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			preparedStatement.executeUpdate();
			ConnectionPool.connectionPool.returnConnection(connection);
		} catch (ConnectionPoolException e) {
			throw new DAOException("error taking connection");
		} catch (SQLException e) {
			throw new DAOException("error edit table");
		}
	}

	@Override
	public void takeBook(String title) throws DAOException {
		String command = "taken";
		editBookTable(title, command);
	}

	@Override
	public void returnBook(String title) throws DAOException {
		String command = "";
		editBookTable(title, command);
	}

	private void editBookTable(String title, String set) throws DAOException {
		try {
			String sql = "UPDATE connpool.books SET available=? WHERE title=?";
			ConnectionPool.PooledConnection connection =
					(ConnectionPool.PooledConnection) ConnectionPool.connectionPool.takeConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, set);
			ps.setString(2, title);
			ps.executeUpdate();
			ConnectionPool.connectionPool.returnConnection(connection);
		} catch (ConnectionPoolException e) {
			throw new DAOException("error taking connection");
		} catch (SQLException e) {
			throw new DAOException("error edit table");
		}
	}

	@Override
	public Book getBookByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

}
