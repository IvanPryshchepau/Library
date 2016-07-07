package by.tr.library.dao.impl;

import java.util.ArrayList;
import java.util.List;

import by.tr.library.bean.Book;
import by.tr.library.dao.UserDao;
import by.tr.library.dao.exception.DAOException;

public class SQLUserDao implements UserDao{

	@Override
	public List<Book> getCatalog() {
		return new ArrayList<Book>();
	}

	@Override
	public boolean registerUser(String login, String password) throws DAOException {
		return false;
	}

	@Override
	public Book takeBook(String title) throws DAOException {
		return null;
	}

	@Override
	public Book returnBook(String title) throws DAOException {
		return null;
	}

	@Override
	public Book getBookByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

}
