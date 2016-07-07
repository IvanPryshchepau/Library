package by.tr.library.dao;

import by.tr.library.bean.Book;
import by.tr.library.dao.exception.DAOException;

public interface AdminDao {
	boolean blockUser(String login) throws DAOException;

	boolean addBook(Book book) throws DAOException;

	boolean deleteBook(Book book) throws DAOException;

}
