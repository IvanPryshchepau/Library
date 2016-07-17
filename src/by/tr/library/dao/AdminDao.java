package by.tr.library.dao;

import by.tr.library.bean.Book;
import by.tr.library.dao.exception.DAOException;

public interface AdminDao {
	void blockUser(String login) throws DAOException;

	void addBook(Book book) throws DAOException;

	void deleteBook(Book book) throws DAOException;

}
