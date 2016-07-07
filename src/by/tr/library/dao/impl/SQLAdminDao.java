package by.tr.library.dao.impl;

import by.tr.library.bean.Book;
import by.tr.library.dao.AdminDao;
import by.tr.library.dao.exception.DAOException;

public class SQLAdminDao implements AdminDao {

	@Override
	public boolean blockUser(String login) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBook(Book book) throws DAOException {
		return false;
	}

}
