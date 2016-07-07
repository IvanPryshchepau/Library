package by.tr.library.dao;

import java.util.List;

import by.tr.library.bean.Book;
import by.tr.library.dao.exception.DAOException;

public interface UserDao {
	Book getBookByTitle(String title) throws DAOException;

	List<Book> getCatalog() throws DAOException;

	boolean registerUser(String login, String password) throws DAOException;

	Book takeBook(String title) throws DAOException;

	Book returnBook(String title) throws DAOException;
}
