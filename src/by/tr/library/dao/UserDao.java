package by.tr.library.dao;

import by.tr.library.bean.Book;
import by.tr.library.dao.exception.DAOException;

import java.util.List;

public interface UserDao {
	Book getBookByTitle(String title) throws DAOException;

	List<Book> getCatalog() throws DAOException;

	void registerUser(String login, String password) throws DAOException;

	void takeBook(String title) throws DAOException;

	void returnBook(String title) throws DAOException;
}
