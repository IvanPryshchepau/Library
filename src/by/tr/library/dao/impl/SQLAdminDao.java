package by.tr.library.dao.impl;

import by.tr.library.bean.Book;
import by.tr.library.dao.AdminDao;
import by.tr.library.dao.exception.DAOException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SQLAdminDao implements AdminDao {

	@Override
	public boolean blockUser(String login) throws DAOException {
		return false;

	}

	@Override
	public boolean addBook(Book book) throws DAOException {
		return false;
	}

	@Override
	public boolean deleteBook(Book book) throws DAOException {
		return false;
	}

}
