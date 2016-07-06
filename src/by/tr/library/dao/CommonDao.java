package by.tr.library.dao;

import by.tr.library.dao.exception.DAOException;

public interface CommonDao {
	boolean aothorization(String login, String password) throws DAOException;
}
