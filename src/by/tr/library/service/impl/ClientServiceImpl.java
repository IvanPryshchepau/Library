package by.tr.library.service.impl;

import by.tr.library.dao.AdminDao;
import by.tr.library.dao.CommonDao;
import by.tr.library.dao.DAOFactory;
import by.tr.library.dao.UserDao;
import by.tr.library.dao.exception.DAOException;
import by.tr.library.service.ClientService;
import by.tr.library.service.exception.ServiceException;

public class ClientServiceImpl implements ClientService{

	@Override
	public boolean logination(String login, String password) throws ServiceException  {
		// parameters validation
		if (login == null || login.isEmpty()){
			return false;
		}
		
		
		DAOFactory factory = DAOFactory.getInstance();
		CommonDao commonDao = factory.getCommonDao();
		
		boolean result;
		try {
			result = commonDao.authorization(login, password);
		} catch (DAOException e) {
			throw new ServiceException("service message", e);
		}

		return result;
	}

	@Override
	public boolean blockUser(String login) throws ServiceException {
		if (login == null || login.isEmpty()) {
			return false;
		}

		DAOFactory factory = DAOFactory.getInstance();
		AdminDao adminDao = factory.getAdminDao();

		boolean result;
		try {
			result = adminDao.blockUser(login);
		} catch (DAOException e) {
			throw new ServiceException("service message", e);
		}

		return result;
	}

	@Override
	public boolean registerUser(String login, String password) throws ServiceException {
		if (login == null || login.isEmpty()) {
			return false;
		}
		DAOFactory factory = DAOFactory.getInstance();
		UserDao userDao = factory.getUserDao();

		boolean result;
		try {
			result = userDao.registerUser(login, password);
		} catch (DAOException e) {
			throw new ServiceException("service message", e);
		}

		return result;
	}

}
